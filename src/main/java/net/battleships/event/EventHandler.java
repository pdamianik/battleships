package net.battleships.event;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {
	private HashMap<Event, HashMap<EventPriority, ArrayList<Handler>>> handlers;

	public EventHandler() {
		this.handlers = new HashMap<>();
	}

	public void register(Handler handler, EventPriority priority) throws InvalidParameterException {
		if (this.handlers.containsKey(handler.getEvent())) {
			HashMap<EventPriority, ArrayList<Handler>> priorityHandler = this.handlers.get(handler.getEvent());
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
		this.register(handler, EventPriority.MEDIUM);
	}

	public void handle(Event event) {
		if (this.handlers.containsKey(event)) {
			HashMap<EventPriority, ArrayList<Handler>> handlersByPriority = this.handlers.get(event);
			if (handlersByPriority.containsKey(EventPriority.HIGH)) {
				for (Handler handler : handlersByPriority.get(EventPriority.HIGH)) {
					handler.handle(event);
				}
			}
			if (handlersByPriority.containsKey(EventPriority.MEDIUM)) {
				for (Handler handler : handlersByPriority.get(EventPriority.MEDIUM)) {
					handler.handle(event);
				}
			}
			if (handlersByPriority.containsKey(EventPriority.LOW)) {
				for (Handler handler : handlersByPriority.get(EventPriority.LOW)) {
					handler.handle(event);
				}
			}
		}
	}
}
