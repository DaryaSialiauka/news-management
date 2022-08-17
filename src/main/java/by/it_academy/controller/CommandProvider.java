package by.it_academy.controller;

import java.util.HashMap;
import java.util.Map;

import by.it_academy.controller.impl.Base;
import by.it_academy.controller.impl.DoAuthentication;
import by.it_academy.controller.impl.DoRegistration;
import by.it_academy.controller.impl.GoToAuthentication;
import by.it_academy.controller.impl.GoToRegistartion;
import by.it_academy.controller.impl.GoToViewNews;
import by.it_academy.controller.impl.SingOut;

public final class CommandProvider {
	
	private final Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new Base());
		commands.put(CommandName.GO_TO_AUTHENTICATION, new GoToAuthentication());
		commands.put(CommandName.DO_AUTHENTICATION, new DoAuthentication());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistartion());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SINGOUT, new SingOut());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		
	}
	
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);
		return command;
	}
	

}
