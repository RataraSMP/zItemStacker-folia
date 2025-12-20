package fr.maxlego08.itemstacker.command.commands;

import fr.maxlego08.itemstacker.ItemStackerPlugin;
import fr.maxlego08.itemstacker.command.VCommand;
import fr.maxlego08.itemstacker.zcore.enums.Message;
import fr.maxlego08.itemstacker.zcore.enums.Permission;
import fr.maxlego08.itemstacker.zcore.utils.commands.CommandType;

public class CommandItemReload extends VCommand {

	public CommandItemReload(ItemStackerPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZITEMSTACKER_RELOAD);
		this.addSubCommand("reload", "rl");
		this.setDescription(Message.DESCRIPTION_RELOAD);
	}

	@Override
	protected CommandType perform(ItemStackerPlugin plugin) {
		
		plugin.reloadFiles();
		message(sender, Message.RELOAD);
		
		return CommandType.SUCCESS;
	}

}
