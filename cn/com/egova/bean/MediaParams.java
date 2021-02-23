package cn.com.egova.bean;

public class MediaParams {
	private String mediaSize;
	private String msgType;
	private String cardID;
	private String msgID;
	private String mediaType;
	private String dealType;
	private String taskID;

	public String getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(String mediaSize) {
		this.mediaSize = mediaSize;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Override
	public String toString() {
		return "MediaParams{" +
				"mediaSize='" + mediaSize + '\'' +
				", msgType='" + msgType + '\'' +
				", cardID='" + cardID + '\'' +
				", msgID='" + msgID + '\'' +
				", mediaType='" + mediaType + '\'' +
				", dealType='" + dealType + '\'' +
				", taskID='" + taskID + '\'' +
				'}';
	}
}
