package net.battleships.event;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {
	private HashMap<Event, HashMap<Integer, ArrayList<Handler>>> handlers;

	public EventHandler() {
		this.handlers = new HashMap<>();
	}

	public void register(Handler handler, int priority) throws InvalidParameterException {
		if (priority < 0 || priority > 2)
			throw new InvalidParameterException("Wrong handler priority, allowed are 0 (high), 1 (medium), 2 (low)");
		if (this.handlers.containsKey(handler.getEvent())) {
			HashMap<Integer, ArrayList<Handler>> priorityHandler = this.handlers.get(handler.getEvent());
			if (priorityHandler.containsKey(priority)) {
				priorityHandler.get(priority).add(handler);
			} else {
				priorityHandler.put(priority, new ArrayList<>() {{
					add(handler);
				}});
			}
		} else {
			this.handlers.put(handler.getEvent(), new HashMap<>() {{
				put(priority, new ArrayList<>() {{
					add(handler);
				}});
			}});
		}
	}

	public void register(Handler handler) {
		this.register(handler, 1);
	}

	public void handle(Event event) {
		if (this.handlers.containsKey(event)) {
			HashMap<Integer, ArrayList<Handler>> handlersByPriority = this.handlers.get(event);
			if (handlersByPriority.containsKey(0)) {
				for (Handler handler : handlersByPriority.get(0)) {
					handler.handle(event);
				}
			}
			if (handlersByPriority.containsKey(1)) {
				for (Handler handler : handlersByPriority.get(1)) {
					handler.handle(event);
				}
			}
			if (handlersByPriority.containsKey(2)) {
				for (Handler handler : handlersByPriority.get(2)) {
					handler.handle(event);
				}
			}
		}
	}
}
