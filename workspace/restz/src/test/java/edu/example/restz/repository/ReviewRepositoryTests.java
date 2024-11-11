package edu.example.restz.repository;

import edu.example.restz.dto.ProductDTO;
import edu.example.restz.dto.ProductListDTO;
import edu.example.restz.dto.ReviewDTO;
import edu.example.restz.entity.Product;
import edu.example.restz.entity.ProductImage;
import edu.example.restz.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Log4j2
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test   //insert 테스트
    public void testInsert(){
        Product product = Product.builder().pno( 1L ).build();

        //GIVEN - Product 엔티티 객체 생성
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Review review = Review.builder()
                    .content("리뷰 테스트" + i)
                    .reviewer("user" + i)
                    .star(5)
                    .product(product)
                    .build();

            //WHEN - 엔티티 저장
            Review savedReview = reviewRepository.save(review);

            //THEN - savedProduct가 널이 아니고 mno는 1일 것
            assertNotNull(savedReview);
            assertEquals(i, savedReview.getRno());
            assertEquals(1, savedReview.getProduct().getPno());
        });
    }

    @Test
    @Transactional(readOnly = true) //읽기 전용 트랜잭션 모드 설정
    public void testRead() {
        Long rno = 5L;

        Optional<Review> foundReview = reviewRepository.findById(rno);
        Review review = foundReview.get();

        Product product = foundReview.get().getProduct();

        assertNotNull(product);
        assertNotNull(review);
        assertEquals(1, product.getPno());
        assertEquals(1000, product.getPrice());
    }

    @Test
    public void testReviewProd(){
        Long rno = 5L;

        Optional<Review> foundReview = reviewRepository.getReviewProduct(rno);
        Review review = foundReview.get();

        Product product = foundReview.get().getProduct();

        assertNotNull(product);
        assertNotNull(review);
        assertEquals(1, product.getPno());
        assertEquals(1000, product.getPrice());
    }

    //리뷰 조회 시 상품 이미지도 가져오기 테스트
    @Test
    public void testReviewProdImage(){
        Long rno = 1L;

        Optional<Review> foundReview = reviewRepository.getReviewProductImg(rno);
        assertTrue(foundReview.isPresent(), "Product should be present");
        log.info(foundReview);

        System.out.println("-----------------------");
        Product product = foundReview.get().getProduct();
        SortedSet<ProductImage> productImages = product.getImages(); //1. foundProduct 객체에서 ProductImage 객체 Set을 가져와서 productImages에 저장
        assertNotNull(productImages);       //2. productImages 널이 아닌지 검증
        assertEquals(0, productImages.first().getIno()); //3. productImages의 첫번째-first()의 ino가 0과 같은지 검증

        log.info(productImages);
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate(){
        Long rno = 5L;
        String content = "리뷰 수정 테스트";
        int star = 1;

        Optional<Review> foundReview = reviewRepository.getReviewProduct(rno); //1. pno에 해당하는 데이터 가져오기
        assertTrue(foundReview.isPresent(), "Product should be present");  //2. 1의 데이터가 존재하는지 검증

        Review review = foundReview.get();
        review.changeContent(content);
        review.changeStar(star);


        foundReview = reviewRepository.getReviewProduct(rno);
        assertEquals(content, foundReview.get().getContent());
        assertEquals(star, foundReview.get().getStar());
    }

    @Test
    @Transactional
    @Commit
    public void testDelete() {
        Long rno = 5L;

        assertTrue(reviewRepository.findById(rno).isPresent(),
                   "Product should be present");  //1. pno에 해당하는 Product 객체가 존재하는지 검증

        reviewRepository.deleteById(rno); //2. pno에 해당하는 Product 객체 삭제

        assertFalse(reviewRepository.findById(rno).isPresent(),
                   "Product should be present");    //3. pno에 해당하는 Product 객체가 존재하지 않는 것을 검증
    }

    @Test   //페이징 테스트
    @Transactional
    public void testlist(){
        Long pno = 1L;
        Pageable pageable = PageRequest.of(0, 5, Sort.by("rno").ascending());

        Page<ReviewDTO> reviewsList = reviewRepository.list(pno,pageable);
        assertNotNull( reviewsList );
        assertEquals(9, reviewsList.getTotalElements()); //전체 게시물 수
        assertEquals(2, reviewsList.getTotalPages());     //총 페이지 수
        assertEquals(0,  reviewsList.getNumber()) ;        //현재 페이지 번호 0
        assertEquals(5, reviewsList.getSize());           //한 페이지 게시물 수 10
        assertEquals(5, reviewsList.getContent().size()); //      "

        reviewsList.getContent().forEach(System.out::println);
    }




//    @Test
//    public void testGetProductDTO(){     //2. ProductRepository의 getProductDTO 메서드 테스트
//        Long pno = 1L;                   //2.0 수정 테스트에서 사용한 상품번호를 이용하여
//
//        Optional<ProductDTO> foundProductDTO = productRepository.getProductDTO(pno);
//        assertTrue(foundProductDTO.isPresent(),      //2.1 반환결과가 존재하는지 검증
//                   "ProductDTO should be present");
//
//        List<String> images = foundProductDTO.get().getImages();
//        assertNotNull(images);                      //2.2 이미지 목록이 널이 아닌지 검증
//        assertEquals("new2.jpg", images.get(3));    //2.3 이미지 목록의 3번째 인덱스의 파일명이 new2.jpg와 같은지 검증
//        log.info(foundProductDTO);
//    }
//
//
//    /*
//    Optional[Product(pno=1, pname=변경 상품, price=1000,
//                    description=상품 설명, registerId=user5,
//                    regDate=2024-08-30T11:09:26.203212,
//                    modDate=2024-08-30T14:18:03.452406)]
//-----------------------
//                    [ProductImage(ino=0, filename=1_image1.jpg),
//                     ProductImage(ino=1, filename=1_image2.jpg),
//                     ProductImage(ino=2, filename=new1.jpg),
//                     ProductImage(ino=3, filename=new2.jpg),
//                     ProductImage(ino=4, filename=new1.jpg),
//                     ProductImage(ino=5, filename=new2.jpg)]    */
//
////    Optional[ProductDTO(pno=1, pname=변경 상품, price=1000,
//    //                    description=상품 설명, registerId=user5,
//    //                    images=[1_image1.jpg, 1_image2.jpg, new1.jpg,
//    //                            new2.jpg, new1.jpg, new2.jpg])]
//
////  ProductListDTO   pno, pname,   price, registerId, pimage
////                     1  변경 상품  1000   user5      1_image1.jpg
////                     2  신규 상품  5000   user5      1_image1.jpg
////                     3  신규 상품  5000   user5      1_image1.jpg
//
//        @Test   //페이징 테스트
//        public void testList(){
//            Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
//
//            Page<ProductListDTO> productList = productRepository.list(pageable);
//            assertNotNull( productList );
//            assertEquals(49, productList.getTotalElements()); //전체 게시물 수
//            assertEquals(5, productList.getTotalPages());     //총 페이지 수
//            assertEquals(0,  productList.getNumber()) ;        //현재 페이지 번호 0
//            assertEquals(10, productList.getSize());           //한 페이지 게시물 수 10
//            assertEquals(10, productList.getContent().size()); //      "
//
//            productList.getContent().forEach(System.out::println);
//        }
//
//
//    @Test   //페이징 테스트
//    public void testListWithAllImagesFetch(){
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
//
////        Page<ProductDTO> productList = productRepository.listWithAllImagesFetch(pageable);
//        Page<ProductDTO> productList = productRepository.getProductDTOQuery(pageable);
//        assertNotNull( productList );
//        assertEquals(100, productList.getTotalElements()); //전체 상품이미지 수
//        assertEquals(10, productList.getTotalPages());     //총 페이지 수
//        assertEquals(0,  productList.getNumber()) ;        //현재 페이지 번호 0
//        assertEquals(10, productList.getSize());           //한 페이지 게시물 수 10
//        assertEquals(10, productList.getContent().size()); //      "
//
//        productList.getContent().forEach(System.out::println);
//    }
//


//    @Test   // @Query 테스트
//    public void testListAll(){
//        Pageable pageable = PageRequest.of(9,   //페이지 번호 - 첫번째 페이지 0부터 시작
//                                           10);  //한 페이지 게시물 수
//
//        Page<Todo> todoPage = todoRepository.listAll(pageable);
//        assertNotNull( todoPage );
//        assertEquals(100, todoPage.getTotalElements()); //전체 게시물 수 100개
//        assertEquals(10, todoPage.getTotalPages());     //총 페이지 수 10개
//        assertEquals(9,  todoPage.getNumber()) ;        //현재 페이지 번호 9
//        assertEquals(10, todoPage.getSize());           //한 페이지 게시물 수 10
//        assertEquals(10, todoPage.getContent().size()); //      "
//
//        todoPage.getContent().forEach(System.out::println);
//    }
//
//    @Test   // Querydsl 테스트
//    public void testSearch(){
//        Pageable pageable = PageRequest.of(9, 10, Sort.by("mno").descending());
//
//        Page<Todo> todoPage = todoRepository.search(pageable);
//        assertNotNull( todoPage );
//        assertEquals(100, todoPage.getTotalElements()); //전체 게시물 수 100개
//        assertEquals(10, todoPage.getTotalPages());     //총 페이지 수 10개
//        assertEquals(9,  todoPage.getNumber()) ;        //현재 페이지 번호 9
//        assertEquals(10, todoPage.getSize());           //한 페이지 게시물 수 10
//        assertEquals(10, todoPage.getContent().size()); //      "
//
//        todoPage.getContent().forEach(System.out::println);
//    }
//
//    @Test
//    public void testGetTodoDTO(){
//        Long mno = 2L;
//        Optional<TodoDTO> foundTodoDTO
//                = todoRepository.getTodoDTO(mno);
//
//        assertNotNull(foundTodoDTO);
//        assertEquals("CHANGER", foundTodoDTO.get().getWriter());
//
//        foundTodoDTO.ifPresent(System.out::println);
//    }
//
//    @Test   // DTO Projections 테스트
//    public void testSearchDTO(){
//        Pageable pageable = PageRequest.of(9, 10, Sort.by("mno").descending());
//
//        Page<TodoDTO> todoPage = todoRepository.searchDTO(pageable);
//        assertNotNull( todoPage );
//        assertEquals(100, todoPage.getTotalElements()); //전체 게시물 수 100개
//        assertEquals(10, todoPage.getTotalPages());     //총 페이지 수 10개
//        assertEquals(9,  todoPage.getNumber()) ;        //현재 페이지 번호 9
//        assertEquals(10, todoPage.getSize());           //한 페이지 게시물 수 10
//        assertEquals(10, todoPage.getContent().size()); //      "
//
//        todoPage.getContent().forEach(System.out::println);
//    }
}











