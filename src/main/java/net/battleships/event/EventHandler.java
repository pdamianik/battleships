package net.battleships.event;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an event handler object to handle events (implements interface Event) with handlers (implements interface Handler)
 * @author Philip Damianik
 * @version 2019-11-05
 */

public class EventHandler {
	private HashMap<Event, HashMap<EventPriority, ArrayList<Handler>>> handlers; // the handlers

	/**
	 * A constructor without magic; simply initializes the handlers hashmap
	 */

	public EventHandler() {
		this.handlers = new HashMap<>();
	}

	/**
	 * Registers a handler to handle an event with a priority
	 * @param handler the handler that handles the event
	 * @param priority the priority for the handler (lower means later executed)
	 */

	public void register(Handler handler, EventPriority priority) {
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

	/**
	 * Registers a handler for an event with medium priority (default)
	 * @param handler the event handler to handle the event
	 */

	public void register(Handler handler) {
		this.register(handler, EventPriority.MEDIUM);
	}

	/**
	 * Handles an event
	 * @param event the event to handle
	 */

	public void handle(Event event) {
		if (this.handlers.containsKey(event)) {
			HashMap<EventPriority, ArrayList<Handler>> handlersByPriority = this.handlers.get(event);
			if (handlersByPriority.containsKey(EventPriority.HIGH))
				for (Handler handler : handlersByPriority.get(EventPriority.HIGH))
					handler.handle(event);
			if (handlersByPriority.containsKey(EventPriority.MEDIUM))
				for (Handler handler : handlersByPriority.get(EventPriority.MEDIUM))
					handler.handle(event);
			if (handlersByPriority.containsKey(EventPriority.LOW))
				for (Handler handler : handlersByPriority.get(EventPriority.LOW))
					handler.handle(event);
		}
	}
}
