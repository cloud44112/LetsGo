package com.sinee.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LeisureSportsDAO {

	public static Connection conn;

	public static void DBC() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String uri = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		conn = DriverManager.getConnection(uri, "hr", "hr");
	}

	public static void main(String[] args) throws Exception {
		boolean flag = false;
		URL url = new URL(
				"https://apis.data.go.kr/B551011/KorService2/locationBasedList2?serviceKey=f1ba68fe57426a4e84ad66e293263a394266c85d43d5ffb6039472a7c54ff9bf&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=C&mapX=126.981611&mapY=37.568477&radius=10000&contentTypeId=28&_type=json");
		BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String result = bf.readLine();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
		JSONObject response = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");

		DBC();

		for (int i = 0; i < item.size(); i++) {
			JSONObject main = (JSONObject) item.get(i);

			System.out.println(main);
			// String leisureSportsNo = "'L' || LPAD(SEQ_LEISURE_SPORTS.NEXTVAL,
			// 3, '0')";
			String title = (String) main.get("title");
			String addr1 = (String) main.get("addr1");
			String addr2 = (String) main.get("addr2");
			String imageUrl = (String) main.get("firstimage");
			String lclsSystm1 = (String) main.get("lclsSystm1");
			String lclsSystm2 = (String) main.get("lclsSystm2");
			String lclsSystm3 = (String) main.get("lclsSystm3");
			int likeCount = 0;
			String mapX = (String) main.get("mapx");
			String mapY = (String) main.get("mapy");

			String sql = "INSERT INTO PLACE "
					+ "(PLACE_ID, LCLSSYSTM1, LCLSSYSTM2, LCLSSYSTM3, TITLE, ADDR1, ADDR2, FIRST_IMAGE, LIKE_COUNT, MAPX, MAPY, PLACE_TYPE) "
					+ "VALUES (SEQ_PLACE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'LEISURE')";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				// stmt.setString(1, leisureSportsNo);
				stmt.setString(1, lclsSystm1);
				stmt.setString(2, lclsSystm2);
				stmt.setString(3, lclsSystm3);
				stmt.setString(4, title);
				stmt.setString(5, addr1);
				stmt.setString(6, addr2);
				stmt.setString(7, imageUrl);
				stmt.setLong(8, likeCount);
				stmt.setString(9, mapX);
				stmt.setString(10, mapY);

				flag = (stmt.executeUpdate() == 1);
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Result: " + flag);
		}
	}
}
