package CsvJsonPojos;

import java.util.ArrayList;
import java.util.List;

public class GroupConfig {

	private String groupName;
	private List<Integer> groupedCols = new ArrayList<Integer>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Integer> getGroupedCols() {
		return groupedCols;
	}

	public void setGroupedCols(List<Integer> groupedCols) {
		this.groupedCols = groupedCols;
	}

}

