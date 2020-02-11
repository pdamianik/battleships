package net.battleships.content.assetResourceParser.nodes;

import java.util.ArrayList;
import java.util.List;

public interface ParentNode extends Node, List<ChildNode> {
	ArrayList<ChildNode> getChildNodes();
}
