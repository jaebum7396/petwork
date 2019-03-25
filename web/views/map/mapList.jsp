<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.*, com.petwork.model.vo.City, com.petwork.model.vo.District, com.petwork.model.vo.Doctor"%>
<%  
	List<City> cityList = (List)request.getAttribute("cityList"); 
	List<District> districtList = (List)request.getAttribute("districtList"); 
	String cityCode = (String)request.getAttribute("cityCode");
	String districtCode = (String)request.getAttribute("districtCode");
	List<Doctor> doctorList = (List)request.getAttribute("doctorList"); 
%>	
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>
<script>
var searchWord = "";
var doctorData = [];

$(function(){
	doctorData = [];
	searchWord = $("#mapCity option:selected").text() + " " + $("#mapDistrict option:selected").text() + " " + $('#searchCategory').val();
 	<% for (int i = 0; i < doctorList.size(); i++)
	{ %>
		doctorData[<%=i%>] = {
			address_name : '<%=doctorList.get(i).getDoctorAddress()%>',
			phone : '<%=doctorList.get(i).getDoctorPhone()%>',
			place_name : '<%=doctorList.get(i).getDoctorHospital()%>',
			x :  '<%=doctorList.get(i).getDoctorX()%>',
			y : '<%=doctorList.get(i).getDoctorY()%>'
		}
	<%}	%>
	$('#keyword').val(searchWord);
	$('#searchFrm').submit();
	showIcon("medical");
})

function fn_mapCategoryChanged(){
	searchWord = "";
	var category = $("#mapCategory option:selected").val();
	switch (category) {
		case "medical": 
			$('#searchCategory').val("병원"); break;
		case "beauty": 
			$('#searchCategory').val("미용"); break;
		case "petFacility": 
			$('#searchCategory').val(" "); break;
	}
	searchWord = $("#mapCity option:selected").text() + " " + $("#mapDistrict option:selected").text() + " " + $('#searchCategory').val();
	$('#keyword').val(searchWord);
	$('#searchFrm').submit();
	showIcon(category);
}

function showIcon(category){
	var iMedical = document.getElementById('iMedical');
	var iBeauty = document.getElementById('iBeauty');
	var iFacility = document.getElementById('iFacility');
	
	switch (category) {
	case 'medical':
		iMedical.style.display = 'block';
		iBeauty.style.display = 'none';
		iFacility.style.display = 'none';
		break;

	case 'beauty':
		iMedical.style.display = 'none';
		iBeauty.style.display = 'block';
		iFacility.style.display = 'none';
		break;
		
	case 'petFacility':
		iMedical.style.display = 'none';
		iBeauty.style.display = 'none';
		iFacility.style.display = 'block';
		break;
	}

}

function fn_mapCityChanged(){
	searchWord = "";
	var cityCode = $("#mapCity option:selected").val();
	var cityName = $("#mapCity option:selected").text();
	var mapCategory = $('#mapCategory').val();
	var changeCity = true;
	doctorData = [];
	
	mapDistrict.options.length = 0;
	$.ajax({
		url:"<%=request.getContextPath()%>/MapServlet",
		type:"post",
		data: {cityCode:cityCode, changeCity:changeCity, cityName:cityName},
		success:function(data){
			var data1=data[0], data2=data[1];
			 
			for (i = 0; i < data1.length; i++)
			{ 
			     $('#mapDistrict').append($('<option>',
			     {
			        value: data1[i]['districtCode'],
			        text : data1[i]['districtName']
			    }));
			}
			
			for (i = 0; i < data2.length; i++)
			{ 
				if(mapCategory == 'medical')
				{
					doctorData[i] = {
						address_name : data2[i]['doctorAddress'],
						phone : data2[i]['doctorPhone'],
						place_name : data2[i]['doctorHospital'],
						x : data2[i]['doctorX'],
						y : data2[i]['doctorY']
					}
				}
			}			
			searchWord = cityName + " " + data1[0]['districtName'] + " " + $('#searchCategory').val();
			$('#keyword').val(searchWord);
			$('#searchFrm').submit();
		}
	});	
}

function fn_mapDistrictChanged(){
	searchWord = "";
	var districtName = $("#mapDistrict option:selected").val();

	searchWord = $("#mapCity option:selected").text() + " " + $("#mapDistrict option:selected").text() + " " + $('#searchCategory').val();
	$('#keyword').val(searchWord);
	$('#searchFrm').submit();
}

</script>
<section>
	<div class="container-fluid">
		<div class='row'>
			<div class='col-md-2' id='nav'></div>
			<div class='col-md-8 mt-2' id='section'>
				<div class="row">
					<div class="col-md-2 pl-0 p-0">
						<select class="form-control" onchange="fn_mapCategoryChanged();" id="mapCategory">
							<option value="medical" selected>병원</option>	
							<option value="beauty">미용</option>								
							<option value="petFacility">애견동반</option>
						</select>
					</div>
					<div class="col-md-1 mt-1">
						<span id="iMedical" style="font-size: 1.5rem; color: #A0D9E2; display: none;"><i class="fas fa-hospital"></i></span>
						<span id="iBeauty" style="font-size: 1.5rem; color: #9E9E9E; display: none;"><i class="fas fa-paw"></i></span>
						<span id="iFacility" style="font-size: 1.5rem; color: #F15F5F; display: none;"><i class="fas fa-heart"></i></span>
					</div>
					<div class="col-md-3"></div>
					<div class="col-md-3 float-right pr-1">
						<select id="mapCity" name="mapCity" onchange="fn_mapCityChanged();" class="form-control p-1">
							<%
								for (City c : cityList) {
							%>
							<option value="<%=c.getCityCode()%>" <%=cityCode.equals(c.getCityCode())? "selected" : "" %>><%=c.getCityName()%></option>
							<%
								}
							%>
						</select>
					</div>
					<div class="col-md-3 float-right pr-0">
						<select id="mapDistrict" name="mapDistrict" onchange="fn_mapDistrictChanged();" class="form-control p-1">						
							<% for (District d : districtList) { %>
								<option value="<%=d.getDistrictCode()%>" <%=districtCode.equals(d.getDistrictCode())? "selected" : "" %>><%=d.getDistrictName()%></option>
							<% } %> 
						</select>
					</div>
				</div>
				<div class="row">
					<div class="map_wrap col-md-12 py-2">
						<div id="map"
							style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
						<div id="menu_wrap" class="bg_white">
							<div class="option" style="display: none;">
								<div>
									<form id="searchFrm" onsubmit="searchPlaces(); return false;">
										키워드 : <input type="text" value="" id="keyword" size="15">
										<button type="submit">검색하기</button>
									</form>
									<input type="hidden" id="searchCategory" value="병원">
								</div>
							</div>
							<hr>
							<ul id="placesList">
							</ul>
							<div id="pagination"></div>
						</div>
					</div>
				</div>
				<div class='col-md-2' id='aside'></div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0163c49c7e470b9d42ba35347acb345a&libraries=services"></script>
<script>
// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;
    
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB); 
}

var medicalNames = [
	"제일 동물병원", "우리 동물병원", "누리봄 동물병원", "쿨펫 동물병원", "마음을 나누는 동물병원", 
	"황동물병원", "월드펫동물병원", "24시 산들산들 동물병원", "CT종합 동물병원", "퍼스 동물병원", 
	"이화 동물병원", "바우미우 동물병원", "24시 스마트 동물병원", "솔샘 동물병원", "이상윤 동물병원",
	"하늘 동물병원", "자스민 동물병원", "박정오 동물병원", "애플펫 동물병원", "우리펫 동물병원", 
	"조아 동물병원", "고운세상 동물병원", "나이스 동물병원", "24시 넬 동물병원", "우리동네 동물병원", 
	"최성공 동물병원", "미니 동물병원", "와우 동물병원", "평화  동물병원", "라파엘 종합동물병원",
	"나누리 동물병원", "나우 24시 동물병원", "토토펫 종합동물병원", "아이조아 애견병원", "뉴욕 동물병원",
	"동물메디컬센터W", "동진 애견병원", "피닉스 동물병원", "더독(THEDOG)동물병원", "(주)라라동물병원",
	"허브 동물병원", "내품에 동물병원", "중앙동물메디컬센터", "프렌즈 동물병원", "뉴월드 동물병원",
	"러브펫 동물병원", "아인스 동물병원", "리더스24시동물병원", "어니스트 동물병원", "장재영 외과동물병원",
	"동물병원 메이", "밝은아이 동물병원", "루비 동물병원", "하노버종합동물병원", "참좋은 동물병원",
	"라온 동물병원", "한빛 동물병원", "오렌지 동물병원", "마리 동물병원", "포레 동물병원",
	"백산 동물병원", "두리틀 동물병원", "24시 푸른 동물병원", "연희 동물병원", "에이플러스 동물병원",
	"함께하는 동물병원", "애니펫 동물병원", "이황 동물병원", "페츠비 동물병원", "이든 동물의료센터",
	"다솜 동물병원", "아프리카 동물병원", "디아크 동물병원", "나래 동물병원", "쓰담쓰담 동물병원"];
	
var beautyNames = [
	"퍼피애견미용샵", "대한애견미용샵", "준애견미용샵", "프로펫애견샵", "이뻐지개애견미용샵",
	"푹쉬개애견미용샵", "트레벨애견미용샵", "한사랑애견미용샵", "행복애견미용샵", "사브리나애견샵",
	"마트애견전문미용샵", "애견샵독스앤휴먼", "허미숙애견미용", "멋내는애견샵", "최덕황애견미용",
	"펫앤펫", "달마시안애견미용", "오렌지애견미용", "캣&독애견미용샵", "조앤리 펫살롱",
	"쿨펫애견미용샵", "코코애견샵", "에이스애견미용샵", "똥강아지애견샵", "펫그루밍 애견미용실",
	"윙크펫", "도그넷 애견미용실", "새봄애견샵", "니니애견미용샵", "행복한 강아지",
	"투니애견미용실", "멍멍'S패밀리", "착한강아지", "홍지연 애견샵", "도그하우스 애견샵",
	"복슬강아지 애견샵", "밍키애견샵", "예삐애견센터", "별애견샵", "VIP애견미용샵",
	"킴스애견미용샵", "오드리애견샵", "포인트애견미용샵", "라온애견미용샵", "강아지 삼총사",
	"바니바니 미용샵", "쥬쥬펫", "굿독", "코지펫샵", "이리온"
];

var facilityNames = [
	"헤이코타[카페]", "아리엘[숙박]", "그리카레[음식점]", "모던몽[숙박]", "비숑포차[음식점]",
	"개떼놀이터[음식점]", "유콜러브잇[숙박]", "교외선[음식점]", "쉼[카페]", "동반[음식점]",
	"라떼킹[카페]", "커피프레지던트[카페]", "카페이누[카페]", "흙과나무[카페]", "기차길[음식점]",
	"시나본[카페]", "토향[음식점]", "우주카페[카페]", "마이치치스[음식점]", "아웃도어키친[음식점]",
	"홍연[음식점]", "대호[음식점]", "초류향[음식점]", "월향[음식점]", "미조리[음식점]",
	"모모야마[음식점]", "산채향[음식점]", "초원[음식점]", "복전문한[음식점]", "뽐모도로[음식점]",
	"La Piazza[음식점]", "사마르칸트[음식점]", "쉐프 다이어리[음식점]", "돈부리[음식점]", "플러스84[음식점]",
	"키친레브쿠헨돈의문[음식점]", "훌리오[음식점]", "콩두[음식점]", "누들스테이션[음식점]", "Food World[음식점]",
	"커피공장[카페]", "카페마마스[카페]", "그레뱅[카페]", "Haru & Ond Day[카페]", "지구마을[카페]",
	"그레이스[카페]", "카페펌킨[카페]", "오시정[카페]", "에이투지카페[카페]", "뎀셀브즈[카페]",
	"카페드마린[카페]", "배롱나무[카페]", "무민앤미[카페]", "틈[카페]", "아름다운커피[카페]",
	"카페달다[카페]", "고요[카페]", "나무사이로[카페]", "드비반트[카페]", "휴플레이스[카페]",
	"area brewers[카페]", "코피티암[카페]", "오무사[카페]", "옐로우카페[카페]", "LINKO[카페]",
	"아재카페[카페]", "Racer[카페]", "Queen's Amore[카페]", "Cafe Genie[카페]", "베아띠[카페]",
	"록시카페[카페]", "프로젝트온더로드[카페]", "카페스프링[카페]", "카페피카[카페]", "카페 프리즌스[카페]",
	"Ps. ArtLu아트루[숙박]", "숲속의 집[숙박]", "신선마을[숙박]", "달빛정원펜션[숙박]", "화수목펜션[숙박]",
	"라임하우스[숙박]", "화이트밸리[숙박]", "쌍떼펜션[숙박]", "수경당펜션[숙박]", "파티가[숙박]",
	"별헤는펜션[숙박]", "앨리스호텔[숙박]", "궁펜션[숙박]", "뷰펜션[숙박]", "에버지오[숙박]",
	"씬스빌호텔[숙박]", "해밀호텔[숙박]", "아바타펜션[숙박]", "자연향기펜션[숙박]", "산내음[숙박]",
	"w&j호텔[숙박]", "벨라지오[숙박]", "가을밤[숙박]", "soi[카페]", "포코앤[카페]"
];


function shuffleRandom(n){
        var arr = new Array();
        var temp;
        var rnum;
       
        //전달받은 매개변수 n만큼 배열 생성 ( 1~n )
        for(var i = 1; i <= n; i++){
            arr.push(i);
        }
 
        //값을 서로 섞기
        for(var i = 0; i < arr.length; i++)
        {
            rnum = Math.floor(Math.random() * n); //난수발생
            temp = arr[i];
            arr[i] = arr[rnum];
            arr[rnum] = temp;
        }
 
        return arr;
}

//장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	var mapCategory = $('#mapCategory').val();
	var cityName =  $("#mapCity option:selected").text();
	var arrRandom;
	if (status === daum.maps.services.Status.OK) {
			// 검색된 장소 이름 변경
			if(mapCategory == 'medical')
			{
				for(var i = 0; i < doctorData.length; i++)
				{
					data[i].place_name = doctorData[i]['place_name'] + " [제휴]";
					data[i].phone = doctorData[i]['phone'];
					data[i].address_name = doctorData[i]['address_name'];
					data[i].road_address_name = doctorData[i]['address_name'];
					data[i].x = doctorData[i]['x'];
					data[i].y = doctorData[i]['y'];
				}
				for (var i = doctorData.length; i < data.length; i++) {
					arrRandom = shuffleRandom(medicalNames.length - 1);
					data[i].place_name = medicalNames[arrRandom[i]];
				} 
			}else if(mapCategory == 'beauty')
			{
				for (var i = 0; i < data.length; i++) {
						arrRandom = shuffleRandom(beautyNames.length - 1);
						data[i].place_name = beautyNames[arrRandom[i]];
				
				}	
			}else if(mapCategory == 'petFacility')
			{
				for (var i = 0; i < data.length; i++) {
					arrRandom = shuffleRandom(facilityNames.length - 1);
					data[i].place_name = facilityNames[arrRandom[i]];
				}
			}
			// 정상적으로 검색이 완료됐으면
			// 검색 목록과 마커를 표출합니다
			displayPlaces(data);
			
			// 페이지 번호를 표출합니다
			displayPagination(pagination);

		} else if (status === daum.maps.services.Status.ZERO_RESULT) {

			alert('검색 결과가 존재하지 않습니다.');
			return;

		} else if (status === daum.maps.services.Status.ERROR) {

			alert('검색 결과 중 오류가 발생했습니다.');
			return;

		}
	}

	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {
		
		var listEl = document.getElementById('placesList'), menuEl = document.getElementById('menu_wrap'), 
		fragment = document.createDocumentFragment(), bounds = new daum.maps.LatLngBounds(), listStr = '';

		// 검색 결과 목록에 추가된 항목들을 제거합니다
		removeAllChildNods(listEl);

		// 지도에 표시되고 있는 마커를 제거합니다
		removeMarker();

		for (var i = 0; i < places.length; i++) {

			// 마커를 생성하고 지도에 표시합니다
			var placePosition = new daum.maps.LatLng(places[i].y, places[i].x), marker = addMarker(
					placePosition, i), itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			// LatLngBounds 객체에 좌표를 추가합니다
			bounds.extend(placePosition);

			// 마커와 검색결과 항목에 mouseover 했을때
			// 해당 장소에 인포윈도우에 장소명을 표시합니다
			// mouseout 했을 때는 인포윈도우를 닫습니다
			(function(marker, title, phone) {
				daum.maps.event.addListener(marker, 'mouseover', function() {
					displayInfowindow(marker, title, phone);
				});

				daum.maps.event.addListener(marker, 'mouseout', function() {
					infowindow.close();
				});

				itemEl.onmouseover = function() {
					displayInfowindow(marker, title, phone);
				};

				itemEl.onmouseout = function() {
					infowindow.close();
				};
			})(marker, places[i].place_name, places[i].phone);
			fragment.appendChild(itemEl);
		}

		// 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
		listEl.appendChild(fragment);
		menuEl.scrollTop = 0;

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		map.setBounds(bounds);
	}

	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

		var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
				+ (index + 1)
				+ '"></span>'
				+ '<div class="info">'
				+ '   <h5>'
				+ places.place_name + '</h5>';

		if (places.road_address_name) {
			itemStr += '    <span>' + places.road_address_name + '</span>'
					+ '   <span class="jibun gray">' + places.address_name
					+ '</span>';
		} else {
			itemStr += '    <span>' + places.address_name + '</span>';
		}

		itemStr += '  <span class="tel">' + places.phone + '</span>' + '</div>';

		el.innerHTML = itemStr;
		el.className = 'item';

		return el;
	}

	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
		var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new daum.maps.Size(36, 37), // 마커 이미지의 크기
		imgOptions = {
			spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new daum.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset : new daum.maps.Point(13, 37)
		// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		}, markerImage = new daum.maps.MarkerImage(imageSrc, imageSize,
				imgOptions), marker = new daum.maps.Marker({
			position : position, // 마커의 위치
			image : markerImage
		});

		marker.setMap(map); // 지도 위에 마커를 표출합니다
		markers.push(marker); // 배열에 생성된 마커를 추가합니다

		return marker;
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}

	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
		var paginationEl = document.getElementById('pagination'), fragment = document
				.createDocumentFragment(), i;

		// 기존에 추가된 페이지번호를 삭제합니다
		while (paginationEl.hasChildNodes()) {
			paginationEl.removeChild(paginationEl.lastChild);
		}

		for (i = 1; i <= pagination.last; i++) {
			var el = document.createElement('a');
			el.href = "#";
			el.innerHTML = i;

			if (i === pagination.current) {
				el.className = 'on';
			} else {
				el.onclick = (function(i) {
					return function() {
						pagination.gotoPage(i);
					}
				})(i);
			}

			fragment.appendChild(el);
		}
		paginationEl.appendChild(fragment);
	}

	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title, phone) {
		var content = '<div style="padding:5px;z-index:1;">' + title + "<br/>" + phone +'</div>';
		infowindow.setContent(content);
		infowindow.open(map, marker);
	}

	// 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {
		while (el.hasChildNodes()) {
			el.removeChild(el.lastChild);
		}
	}

</script>

<%@ include file="/views/common/footer.jsp"%>

