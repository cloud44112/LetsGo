package com.letsgo.place.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.letsgo.place.model.vo.MapScheduleVO;

import com.letsgo.place.model.dao.PostScheduleDAOInterface;
import com.letsgo.place.model.dto.CopyToMyScheduleDTO;
import com.letsgo.place.model.dto.CopyToVisitItemDTO;
import com.letsgo.place.model.vo.PostScheduleVO;
import com.letsgo.place.model.vo.RouteScheduleVO;

public class PostScheduleDAOMB implements PostScheduleDAOInterface {

	private SqlSession session;

	public PostScheduleDAOMB(SqlSession session) {
		this.session = session;
	}

    // ================================================================
    // 전체 게시물 조회 (정렬 4종)
    // ================================================================

    @Override
    public List<PostScheduleVO> getPostScheduleListLike() {
        return session.selectList("PostSchedule.getPostScheduleListLike");
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListView() {
        return session.selectList("PostSchedule.getPostScheduleListView");
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListTitle() {
        return session.selectList("PostSchedule.getPostScheduleListTitle");
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListLatest() {
        return session.selectList("PostSchedule.getPostScheduleListLatest");
    }

    // ================================================================
    // 전체 게시물 키워드 검색 (정렬 4종)
    // LIKE 패턴(%keyword%) 조립은 XML에서 CONCAT으로 처리
    // ================================================================

    @Override
    public List<PostScheduleVO> getPostScheduleListLike(String keyword) {
        return session.selectList("PostSchedule.getPostScheduleListSearchLike", keyword);
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListView(String keyword) {
        return session.selectList("PostSchedule.getPostScheduleListSearchView", keyword);
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListTitle(String keyword) {
        return session.selectList("PostSchedule.getPostScheduleListSearchTitle", keyword);
    }

    @Override
    public List<PostScheduleVO> getPostScheduleListLatest(String keyword) {
        return session.selectList("PostSchedule.getPostScheduleListSearchLatest", keyword);
    }

    // ================================================================
    // 내 게시물 조회 (정렬 4종)
    // ================================================================

    @Override
    public List<PostScheduleVO> getUserPostScheduleListLike(String userId) {
        return session.selectList("PostSchedule.getUserPostScheduleListLike", userId);
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListView(String userId) {
        return session.selectList("PostSchedule.getUserPostScheduleListView", userId);
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListTitle(String userId) {
        return session.selectList("PostSchedule.getUserPostScheduleListTitle", userId);
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListLatest(String userId) {
        return session.selectList("PostSchedule.getUserPostScheduleListLatest", userId);
    }

    // ================================================================
    // 내 게시물 키워드 검색 (정렬 4종)
    // userId + keyword 두 파라미터 → Map 으로 묶어서 전달
    // ================================================================

    @Override
    public List<PostScheduleVO> getUserPostScheduleListLike(String userId, String keyword) {
        return session.selectList("PostSchedule.getUserPostScheduleListSearchLike",
                buildSearchMap(userId, keyword));
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListView(String userId, String keyword) {
        return session.selectList("PostSchedule.getUserPostScheduleListSearchView",
                buildSearchMap(userId, keyword));
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListTitle(String userId, String keyword) {
        return session.selectList("PostSchedule.getUserPostScheduleListSearchTitle",
                buildSearchMap(userId, keyword));
    }

    @Override
    public List<PostScheduleVO> getUserPostScheduleListLatest(String userId, String keyword) {
        return session.selectList("PostSchedule.getUserPostScheduleListSearchLatest",
                buildSearchMap(userId, keyword));
    }

    /** userId + keyword 조합 Map 생성 헬퍼 - LIKE 패턴 조립은 XML에서 CONCAT으로 처리 */
    private Map<String, String> buildSearchMap(String userId, String keyword) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("keyword", keyword);
        return map;
    }

    // ================================================================
    // 상세 데이터 조회
    // ================================================================

    @Override
    public String getBudgetDetail(String postId) {
        return session.selectOne("PostSchedule.getBudgetDetail", postId);
    }

    @Override
    public String getTodoDetail(String postId) {
        return session.selectOne("PostSchedule.getTodoDetail", postId);
    }

    @Override
    public List<RouteScheduleVO> getScheduleRoute(String postId) {
        return session.selectList("PostSchedule.getScheduleRoute", postId);
    }

    @Override
    public List<MapScheduleVO> getMapSchedule(String postId) {
        return session.selectList("PostSchedule.getMapSchedule", postId);
    }

    @Override
    public String getScheduleTitle(String postId) {
        return session.selectOne("PostSchedule.getScheduleTitle", postId);
    }

    @Override
    public int getLikeCount(String postId) {
        Integer result = session.selectOne("PostSchedule.getLikeCount", postId);
        return result != null ? result : 0;
    }

    @Override
    public int getViewCount(String postId) {
        Integer result = session.selectOne("PostSchedule.getViewCount", postId);
        return result != null ? result : 0;
    }

    @Override
    public String getUserId(String postId) {
        return session.selectOne("PostSchedule.getUserId", postId);
    }

    // ================================================================
    // 좋아요 / 조회수 증가
    // ================================================================

    @Override
    public boolean plusLike(String postId) {
        return session.update("PostSchedule.plusLike", postId) == 1;
    }

    @Override
    public boolean plusView(String postId) {
        return session.update("PostSchedule.plusView", postId) == 1;
    }


    @Override
    public boolean deletePostSchedule(String postId) {
    	return session.delete("PostSchedule.deleteSchedulePost", postId) == 1;
            
    }

    @Override
    public boolean deleteVisitItem(String postId) {
    	return session.delete("PostSchedule.deleteVisitItem", postId) == 1;
             

    }

    @Override
    public String copyToMySchedule(String title, String budgetDetail, String todoDetail, String userId) {
        CopyToMyScheduleDTO dto = new CopyToMyScheduleDTO(title, budgetDetail, todoDetail, userId);
        session.insert("PostSchedule.copyToMySchedule", dto);
        return dto.getGeneratedId();
    }

    @Override
    public boolean copyToVisitItem(String myScheduleId, RouteScheduleVO route) {
        CopyToVisitItemDTO dto = new CopyToVisitItemDTO(myScheduleId, route);
        return session.insert("PostSchedule.copyToVisitItem", dto) == 1;
    }
}

