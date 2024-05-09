package com.kt.edu.thirdproject.common;

import lombok.AllArgsConstructor;

import lombok.Getter;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FluxTest {

    @Test
    public void flux_just_consumer() {
        List<String> names = new ArrayList<String>();
        Flux<String> flux = Flux.just("자바","스칼라","파이썬").log();
        flux.subscribe(s -> {
            System.out.println("시퀀스 수신 : " + s);
            names.add(s);
        });
        assertEquals(names, Arrays.asList("자바", "스칼라", "파이썬"));
    }

    //------------------------------------------------------------

    @Test
    public void ArrayTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        for (String s : names) {
            System.out.println("시퀀스 수신 : " + s);
        }
        Iterator iter = names.iterator();
        while (iter.hasNext()) {
            System.out.println("Iter 시퀀스 수신: " + iter.next());
        }
    }

    @Test
    //list받은걸 stream으로 바꿔서 람다식으로 하나씩 출력
    public void ArrayStreamTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        names.stream()
                .filter(s -> s.equals("자바")) // 값이 "자바"인 요소만 필터링
                .forEach(s ->
                        System.out.println("시퀀스 수신 : " + s)
                );
    }

    //filter와 collect 사용하기
    @Test
    public void FilteredListTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        List<String> FilteredList = names.stream()
                .filter(s -> s.equals("자바")) // 값이 "자바"인 요소만 필터링
                .collect(Collectors.toList()); //스트림을 리스트로 반환
        System.out.println("FilteredList :" + FilteredList);

    }

    //------------------------------------------------------------

    @AllArgsConstructor
            // Member 클래스 정의
    class Member {
        @Getter
        private String name;
        @Getter
        private int age;
    }


    // 테스트 메서드
    @Test
    public void ArrayStreamList2() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("자바", 20));
        members.add(new Member("스칼라", 30));
        members.add(new Member("파이썬", 40));

        // 나이만 따로 추출하여 리스트로 만듦
        List<Integer> ages = members.stream()
                .filter(s -> s.getAge() > 30)
                .map(Member::getAge) // Member 객체에서 age 값만 추출
                .collect(Collectors.toList()); // 스트림을 리스트로 반환
        System.out.println("AgeList: " + ages);

        //이름만 따로 추출하여 리스트로 만듦
        List<String> names = members.stream()
                .map(Member::getName) // Member 객체에서 name 값만 추출
                .collect(Collectors.toList()); // 스트림을 리스트로 반환
        System.out.println("NameList: " + names);
    }

    //------------------------------------------------------------

    @Test
    public void SubscriberTest() {
        Flux.range(1, 3) // 1부터 3까지 세 개의 이벤트를 발생시키는 Publisher
        assertEquals(names, Arrays.asList("자바","스칼라","파이썬"));
    }

    // 람다 식을 반복문으로 변경해 보기
    @Test
    public void ArrayTest() {
        List<String> names = new ArrayList<String>();
        names.add("자바");
        names.add("스칼라");
        names.add("파이썬");
        // for 문
        for(String s : names){
            System.out.println("시퀀스 수신 : " + s);
        }

        // while 문
        Iterator iter = names.iterator();
        while(iter.hasNext()){
            System.out.println("Iter 시퀀스 수신 : " + iter.next());
        }
    }

    // Filter와 Collect 사용하기
    @Test
    public void ArrayStreamTest() {
        List<String> names = new ArrayList<String>();
        names.add("자바");
        names.add("스칼라");
        names.add("파이썬");
        List<String> FilteredList = names.stream()
                .filter(s-> s.equals("자바"))
                .collect(Collectors.toList());
        System.out.println("FilteredList : " + FilteredList);

        Flux<String> flux = Flux.fromIterable(names).log();
        flux.subscribe(System.out::println);
        //flux.subscribe(names::add);
        //assertEquals(names, Arrays.asList("자바","스칼라","파이썬"));
    }

    //Member Class 만들고 age 인 값만 별도 List 만들기
    // https://develop-writing.tistory.com/137
    @Test
    public void ArrayStreamTest2() {
        List<Member> names = new ArrayList<>();
        names.add(new Member("자바",20));
        names.add(new Member("스칼라",30));
        names.add(new Member("파이썬",40));

        List<Member> FilteredList = names.stream()
                .filter(s-> s.getAge() > 30)
                .collect(Collectors.toList());
        System.out.println("FilteredList : " + FilteredList);

        List<Integer> ageList = names.stream()
                .map(s -> s.getAge())
                .collect(Collectors.toList());
        System.out.println("ageList : " + ageList);

        List<String> nameList = names.stream()
                .map(Member::getName)
                .collect(Collectors.toList());
        System.out.println("nameList : " + nameList);
    }

    @Test
    public void SubscriberTest() {
        Flux.range(1, 3) // 1부터 3까지 세개의 이벤트를 발생시키는 Publisher

        .subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("[Subscriber] onSubscribe");

                subscription.request(3); //request를 주지 않으면 아무 것도 하지 않는다
//                subscription.request(Long.MAX_VALUE);

            }

            @Override
            public void onNext(Integer item) {
                System.out.println("[Subscriber] onNext: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Subscriber] onError : " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("[Subscriber] onComplete");
            }
        });
    }
    @Test
    public void FluxDefaultTest() {
        Flux.empty().defaultIfEmpty(10).subscribe(System.out::println);
    }

    @Test
    public void flatMapMany(){
        Mono.just(1)
                .flatMapMany(item -> Flux.just(3, 2, 1))
                .subscribe(
                        item -> System.out.println("[Subscriber] onNext : " + item),
                        e -> System.out.println("[Subscriber] onError : " + e.getMessage()),
                        () -> System.out.println("[Subscriber] onComplete")
                );
    }

    @Test
    public void zip(){
        var flux1 = Flux.range(1, 15);
        var flux2 = Flux.range(1, 10).map(it -> it * 10);
        var flux3 = Flux.range(1, 5).map(it -> it * 100);
        Flux.zip(flux1, flux2, flux3)
                .subscribe(item -> System.out.println("[Subscriber] onNext : " + item),
                        e -> System.out.println("[Subscriber] onError : " + e.getMessage()),
                        () -> System.out.println("[Subscriber] onComplete"));
    }
}

@Data
@AllArgsConstructor
class Member {
    private  String name;
    private int age;
}
