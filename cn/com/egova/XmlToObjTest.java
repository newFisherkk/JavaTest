package cn.com.egova;

import cn.com.egova.bean.Video;
import cn.com.egova.bean.VideoList;
import cn.com.egova.utils.DBHelper;
import cn.com.egova.utils.XmlUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class XmlToObjTest {
	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		BufferedReader bf= null;
		try {
			bf = new BufferedReader(new InputStreamReader(
					new FileInputStream("./data/devices.xml"), "gbk"));
			String s = null;
			while((s = bf.readLine())!=null){
				buffer.append(s.trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		VideoList list = null;
//		xml = xml.replaceAll("\n","");
		try{
			list = XmlUtils.toObject(buffer.toString(),"devices",VideoList.class);
			System.out.println(list.toString());
			insert2DB(list);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("插入到sql中出错了");
		} finally {
			if(null!=bf){
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void insert2DB(VideoList list) {
		if (null != list) {
			String sql = " INSERT into tc_video_cell(device_guid,location,device_type_id,valid_flag,group_id,server_id,display_order,video_type,online_flag,videosource,region_id) VALUES(?,?,1,1,1,1,0,0,1,1,100) ";
			DBHelper dbHelper = new DBHelper(sql);
			try {
				dbHelper.conn.setAutoCommit(false);//批量插入
				List<Video> videoList = list.getList();
				Iterator<Video> iterator = videoList.iterator();
				while (iterator.hasNext()) {
					Video video = iterator.next();
					dbHelper.pst.setObject(1, video.getDeviceID());
					dbHelper.pst.setObject(2, video.getAddress());
					dbHelper.pst.addBatch();
				}
				int[] insert = dbHelper.pst.executeBatch();
				dbHelper.conn.commit();
				System.out.println("批量插入数据库表成功，插入数据的数量为：" + insert.length);
				dbHelper.conn.setAutoCommit(true);
			} catch (Exception e) {
				//批量插入发生错误要回滚
				System.out.println("数据库批量插入失败:"+e);
				try {
					if (null != dbHelper.conn) {
						dbHelper.conn.rollback();
						dbHelper.conn.setAutoCommit(true);
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
					System.out.println("数据库批量插入回滚失败:"+ throwables);
				}
				e.printStackTrace();
			} finally {
				if (dbHelper != null)
					dbHelper.close();
			}
		}
	}
}
