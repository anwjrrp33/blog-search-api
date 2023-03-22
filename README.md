# blog-search-api

# 1. 블로그 검색
## Description
* 검색어를 기준으로 블로그 글을 조회하는 검색 API입니다.

## Endpoint
* http://localhost:8080/blog/search

## Request
### Request Method
* GET

### Parameters
| Param |  Type   | Required | Description                                                |
|:-----:|:-------:|:--------:|:-----------------------------------------------------------|
| query | String  |   true   | 검색을 원하는 질의어                                                |
| page  | Integer |  false   | 정렬 방식, ACCURACY(정확도순), RECENCY(최신순), 기본 값은 ACCURACY        |
| size  | Integer |  false   | 결과 페이지 번호                                                  |
| sort  | String  |  false   | 한 페이지에 보여질 항목 수                                            |

## Response
| Param |  Type   | Required | Description                                                |
|:-----:|:-------:|:--------:|:-----------------------------------------------------------|
| blogs |  List   |   true   | 검색을 원하는 질의어                                                |
| page  | Integer |  false   | 정렬 방식, ACCURACY(정확도순), RECENCY(최신순), 기본 값은 ACCURACY        |
| size  | Integer |  false   | 결과 페이지 번호                                                  |

### blogs
|  Param   |  Type  | Description |
|:--------:|:------:|:------------|
|  title   | String | 블로그 글 제목    |
| contents | String | 블로그 글 요약    |
| blogname | String | 블로그 이름      |
|   url    | String | 블로그 글 URL   |
| datetime | String | 블로그 글 작성시간  |

## 예시
### 요청
```
curl  "http://localhost:8080/blog/search?query=우아한유스방&page=1&size=10&start=1&sort=ACCURACY"
```

### 응답
```
{
    "blogs": [
        {
            "title": "<b>우아한</b><b>유스</b><b>방</b> OT",
            "contents": "왔던 부분들을 글로 쓰기 많이 힘들었는데 솔직하게 작성하게 제출하게 되었다. 대략 2주일이 지나고, 내가 작성한 지원서를 Jason님이 좋게 봤는지 선발되었고, <b>우아한</b><b>유스</b><b>방</b> 4기에 참여하게 되었다. 사실 내가 합격할지 몰라서 실감나지 않았다. <b>우아한</b><b>유스</b><b>방</b> 4기 결과 모집 후 얼마 뒤에 첫 번째 OT가 잡혔는데 <b>우아</b>...",
            "blogname": "빵굽는 개발자",
            "url": "http://anwjrrp33.tistory.com/4",
            "datetime": "2023-03-14T20:51:00"
        }
        ...
    ],
    "page": 1,
    "size": 310
}
```

# 2. 인기 검색어 목록
## Description
* 인기 검색어 탑 10까지를 조회하는 검색 API입니다.

## Endpoint
* http://localhost:8080/keyword/rank

## Request
### Request Method
* GET

## Response
|  Param   |  Type   | Description |
|:--------:|:-------:|:------------|
|    id    | Integer | 아이디         |
| keyword  | String  | 키워드         |
|  count   | Integer | 횟수          |

## 예시
### 요청
```
curl  "http://localhost:8080/keyword/rank"
```

### 응답
```
[
    {
        "id": 1,
        "keyword": "우아한유스방",
        "count": 1
    }
]
```

# jar
* https://drive.google.com/file/d/171_ywSzTCYk5iuKOTJgNWjThcZzFxV8S/view?usp=share_link