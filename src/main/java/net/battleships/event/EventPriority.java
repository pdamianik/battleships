package net.battleships.event;

/**
 * These value represent the rough handle order of event handlers (the higher the earlier the handler is fired)
 * @author Philip Damianik
 * @version 2019-11-05
 */

public enum EventPriority {
	/**
	 * Highest handling priority
	 */
	HIGH,
	/**
	 * Normal handling priority
	 */
	MEDIUM,
	/**
	 * Lowest handling priority
	 */
	LOW
}
