class CollectionsApi {
  static #instance = null;

  static getInstance() {
    if(this.#instance == null) {
      this.#instance = new CollectionsApi();
    }
    return this.#instance;
  }

  getCollections(page) {
    let responseData = null;

    const url = location.href;//주소 전체를 들고온다
    const category = url.substring(url.lastIndexOf("/") + 1); //substring = 문자를 잘라라 이며 "/"의 다음 부터

    $.ajax({
      async: false,
      type: "get",
      url: "/api/collections/" + category,
      data: {
        "page": page
      },
      dataType: "json",
      success: (response) => {
        responseData = response.data;
      },
      error: (error) => {
        console.log(error);
      }
    });

    return responseData;
  }
}

class pageScroll { //스코롤 만드는 class, 싱글톤 필요 없음.
  constructor() {
    this.addScrollPagingEvent();
  }

  addScrollPagingEvent() {
    const html = document.querySelector("html");
    const body = document.querySelector("body");

    window.onscroll = () => {
      // console.log("문서 전체 높이: " + body.offsetHeight);
      // console.log("눈에 보이는 영역 높이: " + html.clientHeight);
      // console.log("스크롤의 상단 위치: " + html.scrollTop);
      let scrollStatus = body.offsetHeight - html.clientHeight - html.scrollTop;
      console.log("현재 스크롤 상태: " + scrollStatus);
      if(scrollStatus > -50 && scrollStatus < 50) {
        let nowPage = CollectionsService.getInstance().collectionsEntity.page;
        CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) + 1;
        CollectionsService.getInstance().loadCollections();
      }
    }
  }

}

class CollectionsService {
  static #instance = null;

  static getInstance() {
    if(this.#instance == null) {
      this.#instance = new CollectionsService();
    }
    return this.#instance;
  }

  collectionsEntity = {
      page: 1,
      totalCount: 0,
      maxPage: 0
  }

  loadCollections() {
    if(this.collectionsEntity.page == 1 || this.collectionsEntity.page < Number(this.collectionsEntity.maxPage) + 1) {
      const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
      console.log(responseData)
      if(responseData.length > 0) {
        this.collectionsEntity.totalCount = responseData[0].productTotalCount;
        this.collectionsEntity.maxPage = responseData[0].productTotalCount % 16 == 0 
                                        ? responseData[0].productTotalCount / 16
                                        : Math.floor(responseData[0].productTotalCount /16) + 1;
        this.getCollections(responseData);
      }else {
        alert("해당 카테고리에 등록된 상품 정보기 없습니다.");
        location.href = "/collections/all"
      }
    }
  }

  // 상품 이미지 추가
getCollections(responseData) {
  const collectionProducts = document.querySelector(".collection-products")

  responseData.forEach(product => {
    collectionProducts.innerHTML += `
    <li class="collection-product">
    <div class="product-img">
      <img src="/static/images/product/T.png">
    </div>
    <div class="product-name">
      ${product.productName}
    </div>
    <div class="product-price">
    ${product.productPrice}원
    </div>
  </li>
    `;
  });
}

}

window.onload = () => {
  CollectionsService.getInstance().loadCollections();
  new pageScroll();
}
