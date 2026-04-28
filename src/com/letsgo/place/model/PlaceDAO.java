package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {
	private Connection conn;


	public PlaceDAO() throws Exception{
		conn = DBCP.getConnection();
	}

	public PlaceDAO(Connection conn) throws Exception{
		this.conn = conn;
	}
<<<<<<< HEAD

=======
	
	
>>>>>>> a63038b1c5897900bd7ae2899c121037d8a2c8d9
	public List<PlaceVO> getPlaceByTitle(String placeType, String title){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND title=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, title);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? AND lclssystm3=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, lclssystm3);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public List<PlaceVO> getPlaceOrderByLike(String placeType){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY like_count DESC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public List<PlaceVO> getPlaceOrderByTitle(String placeType){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type=? ORDER BY title ASC";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public List<PlaceVO> getPlaceByAddr(String placeType, String addr){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT place_Id, title, first_image, addr1, mapx, mapy, like_count FROM place WHERE place_type =? AND addr1 Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			String searchKeyword = "%" + addr + "%";
			stmt.setString(1, placeType);
			stmt.setString(2, searchKeyword);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(rs.getString("place_id"), rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy"), rs.getString("first_image"), rs.getInt("like_count"), placeType));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public List<PlaceVO> getPlace(String placeType, String placeId){
		List<PlaceVO> list = new ArrayList<>();

		try {
			String sql = "SELECT title, addr1, mapx, mapy FROM place WHERE place_type=? AND place_Id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, placeType);
			stmt.setString(2, placeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new PlaceVO(placeId, rs.getString("title"), rs.getString("addr1"), 
						rs.getString("mapx"), rs.getString("mapy")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int getPlaceCount(String placeType){
		int count=0;

		try {
			String sql = "SELECT COUNT(place_Id) FROM place WHERE place_type=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, placeType);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getPlaceLikeCount(String placeType, String placeId){
		int count=0;

		try {
			String sql = "SELECT like_count FROM place WHERE place_type=? AND place_Id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, placeType);
			stmt.setString(2, placeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("like_count");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}


    // 바구니에 담긴 플레이스 상세 정보 / 레포츠 담기
    public List<PlaceVO> getPlaces() {
        List<PlaceVO> places = new ArrayList<>();
        String sql = "SELECT place_id, title, addr1, mapx, mapy FROM place";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String placeId = rs.getString("place_id");
                String title = rs.getString("title");
                String addr1 = rs.getString("addr1");
                String mapx = rs.getString("mapx");
                String mapy = rs.getString("mapy");

                PlaceVO place = new PlaceVO(placeId, title, addr1, mapx, mapy);
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    // 내 일정에 넣기
    public boolean insertVisitItem(int visitOrder, int distanceToNext,
            String placeId, String scheduleId, String scheduleType) {

        String sql = "INSERT INTO VISIT_ITEM " +
                "(VISIT_ITEM_ID, VISIT_ORDER, DISTANCE_TO_NEXT, PLACE_ID, SCHEDULE_ID, SCHEDULE_TYPE) " +
                "VALUES (VISIT_ITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, visitOrder);
            pstmt.setInt(2, distanceToNext);
            pstmt.setString(3, placeId);  
            pstmt.setString(4, scheduleId);
            pstmt.setString(5, scheduleType);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // 레포츠 인기순 정렬 조회
    public List<PlaceVO> getLeisurePlacesOrderByLikeDesc() {
        List<PlaceVO> list = new ArrayList<>();
        String sql = "SELECT * FROM place WHERE place_type = 'LEISURE' ORDER BY like_count DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PlaceVO vo = new PlaceVO(
                        rs.getString("place_id"),
                        rs.getString("title"),
                        rs.getString("addr1"),
                        rs.getString("mapx"),
                        rs.getString("mapy"),
                        rs.getString("first_image"),
                        rs.getInt("like_count"),
                        rs.getString("place_type")
                );
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 좋아요 카운트
    public boolean setCounting(String postId) {
        String sql = "UPDATE schedule_post SET like_count = like_count + 1 WHERE post_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, postId);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 플레이스 상세 정보 조회
    public PlaceVO getPlaceById(String placeId) {
        PlaceVO place = null;
        String sql = "SELECT TITLE, ADDR1, MAPX, MAPY FROM PLACE WHERE PLACE_ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, placeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("TITLE");
                    String addr1 = rs.getString("ADDR1");
                    String mapx = rs.getString("MAPX");
                    String mapy = rs.getString("MAPY");
                    place = new PlaceVO(placeId, title, addr1, mapx, mapy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }
    
    
 // SCHEDULE_ID로 방문지 항목 리스트 조회

    public List<VisitItemVO> getVisitItemsByScheduleId(Connection conn, String scheduleId) {
        List<VisitItemVO> list = new ArrayList<>();
        String sql = "SELECT VISIT_ORDER, DISTANCE_TO_NEXT, PLACE_ID " +
                     "FROM VISIT_ITEM " +
                     "WHERE SCHEDULE_ID = ? " +
                     "ORDER BY VISIT_ORDER ASC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, scheduleId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    VisitItemVO vo = new VisitItemVO();
                    vo.setVisitOrder(rs.getInt("VISIT_ORDER"));
                    vo.setDistanceToNext(rs.getDouble("DISTANCE_TO_NEXT"));
                    vo.setPlaceId(rs.getString("PLACE_ID"));
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    // PLACE_ID로 장소 정보 조회

    public PlaceVO getPlaceByPlaceId(Connection conn, String placeId) {

        PlaceVO vo = null;
        String sql = "SELECT PLACE_ID, TITLE, ADDR1, MAPX, MAPY " +
                     "FROM PLACE " +
                     "WHERE PLACE_ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, placeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vo = new PlaceVO(
                        rs.getString("PLACE_ID"),
                        rs.getString("TITLE"),
                        rs.getString("ADDR1"),
                        rs.getString("MAPX"),
                        rs.getString("MAPY")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;

    }

}
