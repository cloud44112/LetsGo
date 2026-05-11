package com.letsgo.place.model;

import java.util.List;

public interface PlaceDAOInterface {
	// 이름으로 플레이스 조회
    List<PlaceVO> getPlaceByTitle(String placeType, String title);

    // 카테고리로 플레이스 조회
    List<PlaceVO> getPlaceByCategory(String placeType, String lclssystm3);

    // 좋아요 순으로 플레이스 조회
    List<PlaceVO> getPlaceOrderByLike(String placeType);

    // 이름 순으로 플레이스 조회
    List<PlaceVO> getPlaceOrderByTitle(String placeType);

    // 지역으로 플레이스 조회
    List<PlaceVO> getPlaceByAddr(String placeType, String addr);

    // 플레이스 담기
    List<PlaceVO> getPlace(String placeId);

    // 플레이스 타입별 전체 개수 조회
    int getPlaceCount(String placeType);

    // 좋아요 수 증가
    boolean setPlaceLikeCount(String placeId);

    // 플레이스 좋아요 수 조회
    int getPlaceLikeCount(String placeType, String placeId);

    // 전체 장소 목록 및 좌표 조회
    List<PlaceVO> getPlaces();

    // 방문 항목 추가
    boolean insertVisitItem(int visitOrder, int distanceToNext,
            String placeId, String scheduleId, String scheduleType);

    // 좋아요 기준 레저 장소 목록 조회
    List<PlaceVO> getLeisurePlacesOrderByLikeDesc();

    // 레저 장소 목록 조회
    List<PlaceVO> getLeisurePlaces();

    // 홈 레저 플레이스 좋아요 카운트 증가
    boolean setCounting(String placeId);

    // 홈 레포츠 플레이스 담기
    PlaceVO getPlaceById(String placeId);

    // SCHEDULE_ID로 방문지 항목 리스트 조회
    List<VisitItemVO> getVisitItemsByScheduleId(String scheduleId);

    // PLACE_ID로 장소 단건 조회
    PlaceVO getPlaceByPlaceId(String placeId);

    // 전체 장소 제목순 검색
    List<PlaceVO> searchPlacesOrderByTitle(String placeType);

    // 전체 장소 좋아요순 검색
    List<PlaceVO> searchPlacesOrderByLike(String placeType);

    // 카테고리 조건 제목순 검색
    List<PlaceVO> searchPlacesByCategoryOrderByTitle(String placeType, String category);

    // 카테고리 조건 좋아요순 검색
    List<PlaceVO> searchPlacesByCategoryOrderByLike(String placeType, String category);

    // 키워드 조건 제목순 검색
    List<PlaceVO> searchPlacesByKeywordOrderByTitle(String placeType, String keyword);

    // 키워드 조건 좋아요순 검색
    List<PlaceVO> searchPlacesByKeywordOrderByLike(String placeType, String keyword);

    // 카테고리 + 키워드 조건 제목순 검색
    List<PlaceVO> searchPlacesByCategoryAndKeywordOrderByTitle(String placeType, String category, String keyword);

    // 카테고리 + 키워드 조건 좋아요순 검색
    List<PlaceVO> searchPlacesByCategoryAndKeywordOrderByLike(String placeType, String category, String keyword);

   
}
