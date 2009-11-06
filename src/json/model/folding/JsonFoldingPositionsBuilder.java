/**
 * 
 */
package json.model.folding;

import java.util.LinkedList;
import java.util.List;

import json.model.jsonnode.JsonNode;
import json.model.jsonnode.JsonType;
import json.model.node.Node;

import org.eclipse.jface.text.Position;

/**
 * @author garner_m
 *
 */
public class JsonFoldingPositionsBuilder {
	
	private List<JsonNode> jsonNodes;
	
	public JsonFoldingPositionsBuilder(List<JsonNode> jsonNodes) {
		this.jsonNodes = jsonNodes;
	}

	public List<Position> buildFoldingPositions() {
		
		List<Position> positions = new LinkedList<Position>();
		List<Position> positionsStack = new LinkedList<Position>();
		if (jsonNodes != null) {
			for (JsonNode jsonNode : jsonNodes) {
				if (isJsonNodeType(jsonNode, JsonType.Array, JsonType.Object)) {
					Position position = new Position(getStart(jsonNode));
					positionsStack.add(0, position);
					positions.add(position);
				}
				
				if (isJsonNodeType(jsonNode, JsonType.End)) {
					if (positionsStack.size() > 0) {
						Position position = positionsStack.remove(0);
						position.setLength(getEnd(jsonNode) - position.getOffset());
					}
				}
			}
		}
		return positions;
	}
	
	private boolean isJsonNodeType(JsonNode jsonNode, JsonType ... jsonTypes) {
		
		for (JsonType jsonType : jsonTypes) {
			if (jsonNode.getJsonType() == jsonType) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getStart(JsonNode jsonNode) {
		Node startNode = jsonNode.getKey();
		if (startNode == null) {
			startNode = jsonNode.getValue();
		}
		
		return startNode.getStart();
	}
	
	public int getEnd(JsonNode jsonNode) {
		
		Node endNode = jsonNode.getValue(); 
		if (endNode == null) {
			endNode = jsonNode.getKey();
		}
		
		if (endNode == null) {
			//System.out.println("Error Node has no end: " + jsonNode.getJsonType());
			return 0;
		}
		return endNode.getEnd();
	}
}
