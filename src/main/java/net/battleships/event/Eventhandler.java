package net.battleships.event;

import java.util.ArrayList;
import java.util.HashMap;

public class Eventhandler {
	private HashMap<Event, ArrayList<Handler>> handlers;

	public Eventhandler() {

	}

	public Eventhandler(ArrayList<Handler> handlers) {

	}

	public void register(Handler handlers) {

	}

	public void register(Handler[] handlers) {

	}

	public void register(ArrayList<Handler> handlers) {

	}

	public void handle(Event event) {

	}
}
