package net.battleships.event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an event handler object for the whole game to call event handlers (event handlers must implement the interface Handler)
 * @author Philip Damianik
 * @version 2019-11-05
 */

public class EventHandler {
	/**
	 * The handlers
	 */
	private HashMap<Class<? extends Event>, HashMap<EventPriority, ArrayList<Handler>>> handlers;

	/**
	 * A constructor without magic; simply initializes the handlers hashmap
	 */

	public EventHandler() {
		this.handlers = new HashMap<>();
	}

	/**
	 * Registers a handler to handle an event with a priority
	 * @param handler the handler that handles the event
	 * @param eventClass the event that the handler will be registered for
	 * @param priority the priority for the handler (lower means later executed)
	 * @see EventPriority
	 */

	public void register(Handler handler, Class<? extends Event> eventClass, EventPriority priority) {
		if (this.handlers.containsKey(eventClass)) {
			HashMap<EventPriority, ArrayList<Handler>> priorityHandler = this.handlers.get(eventClass);
			if (priorityHandler.containsKey(priority)) {
				priorityHandler.get(priority).add(handler);
			} else {
				priorityHandler.put(priority, new ArrayList<>() {{
					add(handler);
				}});
			}
		} else {
			this.handlers.put(eventClass, new HashMap<>() {{
				put(priority, new ArrayList<>() {{
					add(handler);
				}});
			}});
		}
	}

	/**
	 * Registers a handler for an event with medium priority (default)
	 * @param handler the event handler to handle the event
	 * @param eventClass the event that the handler will be registered for
	 */

	public void register(Handler handler, Class<? extends Event> eventClass) {
		this.register(handler, eventClass, EventPriority.MEDIUM);
	}

	/**
	 * Handles an event
	 * @param event the event to handle
	 * @return if the execution was successful
	 */

	public boolean handle(Event event) {
		if (this.handlers.containsKey(event.getClass())) {
			HashMap<EventPriority, ArrayList<Handler>> handlersByPriority = this.handlers.get(event.getClass());
			boolean handled = false;
			for (EventPriority eventPriority : EventPriority.values()) {
				if (handlersByPriority.containsKey(eventPriority))
					for (Handler handler : handlersByPriority.get(eventPriority))
						if (handler.handle(event)) handled = true;
						else return false;
			}
			return handled;
		}
		return false;
	}
}
