package fr.maxlego08.itemstacker.zcore.utils;

import com.tcoded.folialib.FoliaLib;
import fr.maxlego08.itemstacker.zcore.ZPlugin;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Creates and manages a smooth BossBar animation for a group of players.
 */
public class BarAnimation {

    private final BossBar bossBar;
    private final double totalTime;
    private double remainingTime;
    private WrappedTask task;
    private final FoliaLib foliaLib;

    /**
     * Creates a new smooth BossBar animation for a group of players.
     *
     * @param plugin   the plugin instance.
     * @param players  the list of players to display the BossBar to.
     * @param text     the text to display on the BossBar.
     * @param seconds  the total duration of the animation in seconds.
     * @param barColor the color of the BossBar.
     * @param barStyle the style of the BossBar.
     */
    public BarAnimation(Plugin plugin, List<Player> players, String text, int seconds, BarColor barColor, BarStyle barStyle) {
        this.bossBar = Bukkit.createBossBar(text, barColor, barStyle);
        this.totalTime = seconds * 20.0; // Convert seconds to ticks (20 ticks = 1 second)
        this.remainingTime = totalTime;
        this.foliaLib = ZPlugin.getFoliaLib();

        for (Player player : players) {
            this.bossBar.addPlayer(player);
        }

        this.bossBar.setVisible(true);

        this.foliaLib.getScheduler().runTimer(wrappedTask -> run(new WrappedTask(wrappedTask)), 1, 1);
    }

    /**
     * The task to run on each tick. Updates the BossBar's progress.
     * Cancels the task and removes the BossBar when the time runs out.
     */
    public void run(WrappedTask task) {
        double progress = remainingTime / totalTime;
        
        if (progress >= 0 && progress <= 1) {
            bossBar.setProgress(progress);
        }

        if (remainingTime <= 0) {
            bossBar.removeAll();
            if (task != null) task.cancel(); // Stop the task when the BossBar is empty
        }

        remainingTime -= 1; // Decrease by 1 tick at each update
    }

    private static class WrappedTask {
        private final Object task;

        public WrappedTask(Object task) {
            this.task = task;
        }

        public void cancel() {
            if (task instanceof com.tcoded.folialib.wrapper.task.WrappedTask) {
                ((com.tcoded.folialib.wrapper.task.WrappedTask) task).cancel();
            } else if (task instanceof org.bukkit.scheduler.BukkitTask) {
                ((org.bukkit.scheduler.BukkitTask) task).cancel();
            }
        }
    }
}
