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

class PageNumber {
  #page = 0;
  // #totalCount = 0;
  #maxPageNumber = 0;
  #pageNumberList = null;

  constructor(page, totalCount) {
    this.#page = page;
    this.#totalCount = totalCount;
    this.#maxPageNumber = totalCount % 16 == 0 ? math.floor(totalCount / 16) : Math.floor(totalCount / 16) + 1;
    this.#pageNumberList = document.querySelector(".page-number-list");
    this.#pageNumberList.innerHTML = "";
    this.loadPageNumbers();
  }

  loadPageNumbers() {
    this.createPreButton();
    this.createNumberButtons();
    this.createNextButton();
    this.addPageButtonEvent();
  }

  createPreButton() {
    if(this.#page != 1) {
      this.#pageNumberList.innerHTML += `
       <a href="javascript:void(0)"><li>&#60;</li></a>
      `;
    }
  }

  createNumberButtons () {
    const startIndex = this.#page % 5 == 0 ? this.#page - 4 : this.#page - (this.#page % 5) + 1;
    const endIndex = startIndex + 4 <= this.#maxPageNumber ? startIndex + 4 : this.#maxPageNumber;

    for(let i = startIndex; i <= endIndex; i++) {
      this.#pageNumberList.innerHTML += `
      <a href="javascript:void(0)"><li>${i}</li></a>
      `;
    }
  }
  
  createNextButton() {
    if(this.#page != this.#maxPageNumber) {
      this.#pageNumberList.innerHTML += `
      <a href="javascript:void(0)"><li>&#62;</li></a>
     `;
    }
  }

  addPageButtonEvent() {
      const pageButtons = this.#pageNumberList.querySelectorAll("li");
      pageButtons.forEach(button => {
          button.onclick = () => {
              if(button.textContent == "<") {
                  const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                  CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) - 1;
                  CollectionsService.getInstance().loadCollections();

              }else if(button.textContent == ">") {
                  const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                  CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) + 1;
                  CollectionsService.getInstance().loadCollections();

              }else {
                const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                if(button.textContent != nowPage) {
                  CollectionsService.getInstance().collectionsEntity.page = button.textContent;
                  CollectionsService.getInstance().loadCollections();
                }
              }
          }
      });
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
      totalCount: 0
  }

  loadCollections(page) {
    const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
    if(responseData.length > 0) {
      this.collectionsEntity.totalCount = responseData[0].productTotalCount;
      new PageNumber(this.collectionsEntity.page, this.collectionsEntity.totalCount);
      this.getCollections(responseData);
    }else {
      alert("해당 카테고리에 등록된 상품 정보기 없습니다.");
      location.href = "/collections/all"
    }
  }

  // 상품 이미지 추가
getCollections(responseData) {
  const collectionProducts = document.querySelector(".collection-products")
  collectionProducts.innerHTML = ``;

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
}
