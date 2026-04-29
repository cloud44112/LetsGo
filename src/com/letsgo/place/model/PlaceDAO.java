package com.letsgo.place.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {
    private Connection conn;

    public PlaceDAO(Connection conn){
        this.conn = conn;
    }
    
    // 이름으로 플레이스 조회
    public List<PlaceVO> getPlaceByTitle(String placeType, String title) {
        List<PlaceVO> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BY_TITLE_SQL);
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

    // 카테고리로 플레이스 조회
    public List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3) {
        List<PlaceVO> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BY_CATEGORY_SQL);
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

    // 좋아요 순으로 플레이스 조회
    public List<PlaceVO> getPlaceOrderByLike(String placeType) {
        List<PlaceVO> list = new ArrayList<>();
        try {
           PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_ORDER_BY_LIKE_SQL);
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

    // 이름 순으로 플레이스 조회
    public List<PlaceVO> getPlaceOrderByTitle(String placeType) {
        List<PlaceVO> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_ORDER_BY_TITLE_SQL);
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

    // 지역으로 플레이스 조회
    public List<PlaceVO> getPlaceByAddr(String placeType, String addr) {
        List<PlaceVO> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BY_ADDR_SQL);
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

    // 플레이스 담기
    public List<PlaceVO> getPlace(String placeId) {
        List<PlaceVO> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BY_PLACE_ID_SQL);
            stmt.setString(1, placeId);
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

    // 플레이스 타입별 전체 개수 조회
    public int getPlaceCount(String placeType) {
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_COUNT_SQL);
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

    // 좋아요 수 증가
    public boolean setPlaceLikeCount(String placeId) {
       try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.ADD_PLACE_LIKE_COUNT_SQL)) {
            pstmt.setString(1, placeId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 플레이스 좋아요 수 조회
    public int getPlaceLikeCount(String placeType, String placeId) {
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(PlaceQuery.GET_PLACE_LIKE_COUNT_SQL);
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

    // 전체 장소 목록 및 좌표 조회ㅜㅜ
    public List<PlaceVO> getPlaces() {
        List<PlaceVO> places = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.GET_PLACES_SQL);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PlaceVO place = new PlaceVO(
                        rs.getString("place_id"),
                        rs.getString("title"),
                        rs.getString("addr1"),
                        rs.getString("mapx"),
                        rs.getString("mapy"));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    // 방문 항목 추가
    public boolean insertVisitItem(int visitOrder, int distanceToNext,
            String placeId, String scheduleId, String scheduleType) {
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.INSERT_VISIT_ITEM_SQL)) {
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

    // 좋아요 기준 레저 장소 목록 조회
    public List<PlaceVO> getLeisurePlacesOrderByLikeDesc() {
        List<PlaceVO> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.GET_LEISURE_PLACE_DESC_SQL);
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
                        rs.getString("place_type"));
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 게시글 좋아요 카운트 증가
    public boolean setCounting(String postId) {
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.SET_COUNTING_SQL)) {
            pstmt.setString(1, postId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 플레이스 단건 조회
    public PlaceVO getPlaceById(String placeId) {
        PlaceVO place = null;
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BYID_SQL)) {
            pstmt.setString(1, placeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    place = new PlaceVO(placeId,
                            rs.getString("TITLE"),
                            rs.getString("ADDR1"),
                            rs.getString("MAPX"),
                            rs.getString("MAPY"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    // SCHEDULE_ID로 방문지 항목 리스트 조회
    public List<VisitItemVO> getVisitItemsByScheduleId(String scheduleId) {
        List<VisitItemVO> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.GET_VISIT_ITEM_SCHEDULE_ID_SQL)) {
            pstmt.setString(1, scheduleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    VisitItemVO vo = new VisitItemVO();
                    vo.setVisitOrder(rs.getInt("VISIT_ORDER"));
                    double distanceToNext = rs.getDouble("DISTANCE_TO_NEXT");
                    vo.setDistanceToNext(rs.wasNull() ? 0.0 : distanceToNext);
                    vo.setPlaceId(rs.getString("PLACE_ID"));
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // PLACE_ID로 장소 단건 조회
    public PlaceVO getPlaceByPlaceId(String placeId) {
        PlaceVO vo = null;
        try (PreparedStatement pstmt = conn.prepareStatement(PlaceQuery.GET_PLACE_BY_PLACE_ID_SQL)) {
            pstmt.setString(1, placeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vo = new PlaceVO(
                            rs.getString("PLACE_ID"),
                            rs.getString("TITLE"),
                            rs.getString("ADDR1"),
                            rs.getString("MAPX"),
                            rs.getString("MAPY"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
  // 장소명,지역 검색 거리, 좋아요순 정렬 모두 적용
    public List<PlaceVO> searchPlaces(String placeType, String category, String keyword, String sortType) {
        List<PlaceVO> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder(PlaceQuery.GET_PLACE); 
        
        if (category != null && !category.trim().isEmpty()) {
            sql.append(PlaceQuery.BY_CATEGORY);
        }
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND (title LIKE ? OR addr1 LIKE ?) ");
        }
        
        if ("popular".equals(sortType)) {
            sql.append(PlaceQuery.ORDER_BY_LIKE);
        } else {
            sql.append(PlaceQuery.ORDER_BY_TITLE);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            stmt.setString(idx++, placeType); 

            if (category != null && !category.trim().isEmpty()) {
                stmt.setString(idx++, category);
            }
            
            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchPattern = "%" + keyword + "%";
                stmt.setString(idx++, searchPattern);
                stmt.setString(idx++, searchPattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new PlaceVO(
                        rs.getString("place_id"),
                        rs.getString("title"),
                        rs.getString("addr1"),
                        rs.getString("mapx"),
                        rs.getString("mapy"),
                        rs.getString("first_image"),
                        rs.getInt("like_count"),
                        placeType
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}