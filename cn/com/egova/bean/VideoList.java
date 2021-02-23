package cn.com.egova.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("devices")
public class VideoList {

	@XStreamImplicit(itemFieldName = "item")
	private List<Video> list;

	public List<Video> getList() {
		return list;
	}

	public void setList(List<Video> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "VideoList{" +
				"list=" + list.toString() +
				'}';
	}
}
