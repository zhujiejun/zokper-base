package com.zhujiejun.zokper.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {

    private static final List<String> fightCompany = Lists.newArrayList("CSA", "CEA", "HNA");

    private static FightQueryTask createQueryTask(String flight, String original, String dest) {
        return new FightQueryTask(flight, original, dest);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = Lists.newArrayList();

        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createQueryTask(f, original, dest)).collect(Collectors.toList());

        tasks.forEach(Thread::start);

        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(FightQuery::get).forEach(result::addAll);

        return result;
    }

    public static void main(String[] args) {
        String original = "SZ";
        String dest = "SH";
        List<String> result = search(original, dest);
        System.out.println("=======================================");
        result.forEach(System.out::println);
    }
}
