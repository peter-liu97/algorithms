package com.javase;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {
    List<Person> list = new ArrayList();
    @Test
    public void test(){

    }
    @Test
    public void createStream() throws FileNotFoundException {
        List<String> nameList = Arrays.asList("Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter");
        String[] nameArr = {"Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter"};
        // 集合获取 Stream 流
        Stream<String> stream = nameList.stream();
        //获取行流
        Stream<String> stringStream = nameList.parallelStream();
        //通过Stream.of获取流
        Stream<List<String>> nameList1 = Stream.of(nameList);
        //获取数组的流
        Stream<String> stream1 = Arrays.stream(nameArr);
        //文件获取stream流
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("README.md"));
//        Stream<String> lines = bufferedReader.lines();
        // 从静态方法获取流操作
        IntStream range = IntStream.range(1, 10);
        range.limit(10).forEach(num -> System.out.print(num + ","));
        IntStream intStream = IntStream.of(1, 2, 3, 3, 4);
        intStream.forEach(num -> System.out.print(num+","));

    }
    @Test
    public void mapTest() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = numberList.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
        collect.stream()
                .forEach(number -> System.out.println(number));
    }
    //filter：数据筛选，相当于if判断
    @Test
    public void filterTest() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = numberList.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        collect.stream()
                .forEach(number -> System.out.println(number));
    }
    //distinct：去重
    @Test
    public void distinctTest(){
        List<String> list = Arrays.asList("AA", "BB", "CC", "BB", "CC", "AA", "AA");
        long count = list.stream()
                .distinct().count();
        System.out.println(count);
        String collect = list.stream().distinct().collect(Collectors.joining("/"));
        System.out.println(collect);
    }
    /**
     * sorted
     * peek
     * limit：获取前n个元素
     * skip：丢弃前n个元素
     */
    @Test
    public void limitOrSkipTest() {
        List<Integer> ageList = Arrays.asList(11, 22, 13, 14, 25, 26);
        ageList.stream()
                .limit(3)
                .forEach(age -> System.out.println(age));
        ageList.stream()
                .skip(3)
                .forEach(age -> System.out.println(age));
    }
    //parallel：并行流
    @Test
    public void parallelTest(){
        long reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel().reduce(1, Long::sum);
        System.out.println(reduce);
    }
    @Test
    public void uuid(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
    @Test
    public void testTread(){
        new Thread(()->{

        }).start();
    }
}
