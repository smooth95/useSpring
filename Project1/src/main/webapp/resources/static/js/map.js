console.log("test111111")
document.ready
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options);
var markerImage = new kakao.maps.MarkerImage(
	'https://cdn-icons-png.flaticon.com/512/3082/3082365.png',
	new kakao.maps.Size(31, 35), new kakao.maps.Point(13, 34));


var positions = [
	{
		title : "카카오2",
		position : new kakao.maps.LatLng(33.451701, 126.571667)	
	},
	{
		title : "amap2",
		position : new kakao.maps.LatLng(33.452000, 126.572000)	
	},
	{
		title : "map3",
		position : new kakao.maps.LatLng(33.450500, 126.570000)	
	},
	{
		title : "map3",
		position : new kakao.maps.LatLng(33.450013, 126.570180)	
	},
	{
		title : "map3",
		position : new kakao.maps.LatLng(33.451560, 126.570660)	
	},
	{
		title : "map3",
		position : new kakao.maps.LatLng(33.449791, 126.571138)	
	},
	{
		title : "map3",
		position : new kakao.maps.LatLng(33.452123, 126.572730)	
	},
]


var marker = new kakao.maps.Marker({
	map : map,
	position : new kakao.maps.LatLng(33.450701, 126.570667),
	title : "카카오"
})

positions.forEach(function(pos) {
	var center = map.getCenter()
	
	var distance = getDistance(center, pos.position)
	
	if (distance <= 100) {
		var marker = new kakao.maps.Marker({
			position : pos.position,
			title : pos.title
		})
		marker.setMap(map)
	} else {
		var marker = new kakao.maps.Marker({
			position : pos.position,
			title : pos.title
		})
		marker.setImage(markerImage)
		marker.setMap(map)
	}
	
	
})


kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;
	var center = map.getCenter()
	console.log(getDistance(center, latlng))
});

function getDistance(start, end) {
	var polyline = new kakao.maps.Polyline({
	    map: map,
	    path: [
	        start,
	        end
	    ],
	    strokeOpacity: 0,
	});
	
	var length = polyline.getLength()
	polyline.setMap(null);
	return length;
}

marker.setRange(100)





var circle = new kakao.maps.Circle({
    map: map,
    center : new kakao.maps.LatLng(33.450701, 126.570667),
    radius: 100,
    strokeWeight: 2,
    strokeColor: '#FF00FF',
    strokeOpacity: 0.8,
    strokeStyle: 'dashed',
    fillColor: '#00EEEE',
    fillOpacity: 0.5 
});




