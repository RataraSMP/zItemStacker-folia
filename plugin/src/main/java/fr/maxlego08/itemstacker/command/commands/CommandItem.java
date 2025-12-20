package fr.maxlego08.itemstacker.command.commands;

import fr.maxlego08.itemstacker.ItemStackerPlugin;
import fr.maxlego08.itemstacker.command.VCommand;
import fr.maxlego08.itemstacker.zcore.enums.Permission;
import fr.maxlego08.itemstacker.zcore.utils.commands.CommandType;

public class CommandItem extends VCommand {

	public CommandItem(ItemStackerPlugin plugin) {
		super(plugin);
		this.setPermission(Permission.ZITEMSTACKER_USE);
		this.addSubCommand(new CommandItemReload(plugin));
		this.addSubCommand(new CommandItemVersion(plugin));
	}

	@Override
	protected CommandType perform(ItemStackerPlugin plugin) {
		syntaxMessage();
		return CommandType.SUCCESS;
	}

}
