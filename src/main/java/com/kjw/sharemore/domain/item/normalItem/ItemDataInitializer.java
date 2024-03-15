package com.kjw.sharemore.domain.item.normalItem;

import com.kjw.sharemore.domain.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemSearchRepository;
import com.kjw.sharemore.domain.item.normalItem.service.ItemSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("ItemDataInitializer")
@RequiredArgsConstructor
public class ItemDataInitializer {

    private final ItemSearchService itemSearchService;
    private final ItemSearchRepository itemSearchRepository;

    public void init() {
        ItemDocument itemDocument = ItemDocument.builder()
                        .id("1")
                        .user("김철수")
                        .name("책상")
                        .description("나무로 만든 책상")
                        .category("가구")
                        .price(50000)
                        .itemImage("https://source.unsplash.com/random/?desk")
                        .build();

        ItemDocument itemDocument2 = ItemDocument.builder()
                .id("2")
                        .user("이영희")
                        .name("의자")
                        .description("편안한 의자")
                        .category("가구")
                        .price(30000)
                        .itemImage("https://source.unsplash.com/random/?chair")
                        .build();

        ItemDocument itemDocument3 = ItemDocument.builder()
                        .id("3")
                        .user("박지성")
                        .name("노트북")
                        .description("빠른 속도의 노트북")
                        .category("전자제품")
                        .price(1000000)
                        .itemImage("https://source.unsplash.com/random/?laptop")
                        .build();

        ItemDocument itemDocument4 = ItemDocument.builder()
                        .id("4")
                        .user("최유나")
                        .name("휴대폰")
                        .description("새로운 모델의 휴대폰")
                        .category("전자제품")
                        .price(800000)
                        .itemImage("https://source.unsplash.com/random/?mobile")
                        .build();

        ItemDocument itemDocument5 = ItemDocument.builder()
                        .id("5")
                        .user("김태현")
                        .name("스니커즈")
                        .description("편안한 스니커즈")
                        .category("의류")
                        .price(70000)
                        .itemImage("https://source.unsplash.com/random/?sneakers")
                        .build();

        ItemDocument itemDocument6 = ItemDocument.builder()
                        .id("6")
                        .user("이민준")
                        .name("셔츠")
                        .description("시원한 셔츠")
                        .category("의류")
                        .price(40000)
                        .itemImage("https://source.unsplash.com/random/?shirt")
                        .build();

        ItemDocument itemDocument7 = ItemDocument.builder()
                        .id("7")
                        .user("박서아")
                        .name("커피머신")
                        .description("아침에 좋은 커피머신")
                        .category("주방용품")
                        .price(200000)
                        .itemImage("https://source.unsplash.com/random/?coffeemachine")
                        .build();

        ItemDocument itemDocument8 = ItemDocument.builder()
                        .id("8")
                        .user("최준영")
                        .name("냉장고")
                        .description("넓은 냉장고")
                        .category("가전제품")
                        .price(1500000)
                        .itemImage("https://source.unsplash.com/random/?refrigerator")
                        .build();

        ItemDocument itemDocument9 = ItemDocument.builder()
                        .id("9")
                        .user("김하은")
                        .name("에어컨")
                        .description("시원한 에어컨")
                        .category("가전제품")
                        .price(1200000)
                        .itemImage("https://source.unsplash.com/random/?airconditioner")
                        .build();

        ItemDocument itemDocument10 = ItemDocument.builder()
                        .id("10")
                        .user("이지아")
                        .name("자전거")
                        .description("편안한 자전거")
                        .category("운동용품")
                        .price(300000)
                        .itemImage("https://source.unsplash.com/random/?bicycle")
                        .build();

        ItemDocument itemDocument11 = ItemDocument.builder()
                        .id("11")
                        .user("김철수")
                        .name("운동화")
                        .description("편안한 운동화")
                        .category("의류")
                        .price(80000)
                        .itemImage("https://source.unsplash.com/random/?sneakers")
                        .build();

        ItemDocument itemDocument12 = ItemDocument.builder()
                        .id("12")
                        .user("이영희")
                        .name("침대")
                        .description("푹신한 침대")
                        .category("가구")
                        .price(250000)
                        .itemImage("https://source.unsplash.com/random/?bed")
                        .build();

        ItemDocument itemDocument13 = ItemDocument.builder()
                        .id("13")
                        .user("박지성")
                        .name("TV")
                        .description("큰 화면의 TV")
                        .category("가전제품")
                        .price(2000000)
                        .itemImage("https://source.unsplash.com/random/?tv")
                        .build();

        ItemDocument itemDocument14 = ItemDocument.builder()
                        .id("14")
                        .user("최유나")
                        .name("이어폰")
                        .description("소리가 좋은 이어폰")
                        .category("전자제품")
                        .price(150000)
                        .itemImage("https://source.unsplash.com/random/?earphones")
                        .build();

        ItemDocument itemDocument15 = ItemDocument.builder()
                        .id("15")
                        .user("김태현")
                        .name("헤드폰")
                        .description("소리가 좋은 헤드폰")
                        .category("전자제품")
                        .price(200000)
                        .itemImage("https://source.unsplash.com/random/?headphones")
                        .build();

        ItemDocument itemDocument16 = ItemDocument.builder()
                        .id("16")
                        .user("이민준")
                        .name("식탁")
                        .description("나무로 만든 식탁")
                        .category("가구")
                        .price(150000)
                        .itemImage("https://source.unsplash.com/random/?diningtable")
                        .build();

        ItemDocument itemDocument17 = ItemDocument.builder()
                        .id("17")
                        .user("박서아")
                        .name("책장")
                        .description("나무로 만든 책장")
                        .category("가구")
                        .price(100000)
                        .itemImage("https://source.unsplash.com/random/?bookshelf")
                        .build();

        ItemDocument itemDocument18 = ItemDocument.builder()
                        .id("18")
                        .user("최준영")
                        .name("소파")
                        .description("편안한 소파")
                        .category("가구")
                        .price(300000)
                        .itemImage("https://source.unsplash.com/random/?sofa")
                        .build();

        ItemDocument itemDocument19 = ItemDocument.builder()
                        .id("19")
                        .user("김하은")
                        .name("세탁기")
                        .description("능률적인 세탁기")
                        .category("가전제품")
                        .price(800000)
                        .itemImage("https://source.unsplash.com/random/?washingmachine")
                        .build();

        ItemDocument itemDocument20 = ItemDocument.builder()
                        .id("20")
                        .user("이지아")
                        .name("청바지")
                        .description("편안한 청바지")
                        .category("의류")
                        .price(50000)
                        .itemImage("https://source.unsplash.com/random/?jeans")
                        .build();

        ItemDocument itemDocument21 = ItemDocument.builder()
                        .id("21")
                        .user("김철수")
                        .name("책상")
                        .description("나무로 만든 책상")
                        .category("가구")
                        .price(55000)
                        .itemImage("https://source.unsplash.com/random/?desks")
                        .build();

        itemSearchRepository.deleteAll();
        itemSearchRepository.saveAll(Arrays.asList(itemDocument, itemDocument2, itemDocument3, itemDocument4, itemDocument5, itemDocument6, itemDocument7, itemDocument8, itemDocument9, itemDocument10, itemDocument11, itemDocument12, itemDocument13, itemDocument14, itemDocument15, itemDocument16, itemDocument17, itemDocument18, itemDocument19, itemDocument20, itemDocument21));
    }

    
}
