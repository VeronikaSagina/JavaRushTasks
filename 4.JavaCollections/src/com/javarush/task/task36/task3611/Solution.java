package com.javarush.task.task36.task3611;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humansRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humansRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends1(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              //expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           //expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int person, int deep) {
        Set<Integer> friends = new TreeSet<>();
        if (deep == 0){
            Set<Integer> set = new HashSet<>();
            set.add(person);
            return set;
        }
        if (deep == 1){
            return getFriends(person);
        }
        Set<Integer> closeFriends = getFriends(person);
        for (Integer closeFriend : closeFriends) {
            friends.addAll(getAllFriendsAndPotentialFriends(closeFriend, deep - 1));
        }
        friends.remove(person);
        return friends;
    }

    public Set<Integer> getAllFriendsAndPotentialFriends1(int person, int deep) {
        Set<Integer> allFriends = new HashSet<>();
        allFriends.add(person);
        Set<Integer> nextCircle = new HashSet<>(allFriends);
        while (deep-- > 0) {
            Set<Integer> currentCircle = nextCircle;
            nextCircle = new HashSet<>();
            for (Integer friend : currentCircle) {
                nextCircle.addAll(getFriends(friend));
            }
            nextCircle.removeAll(allFriends);
            allFriends.addAll(nextCircle);
        }
        allFriends.remove(person);
        return new TreeSet<>(allFriends);
    }

    private Set<Integer> getFriends(int person) {
        Set<Integer> friends = new HashSet<>();
        for (int friend = 0; friend < humansRelationships.length; friend++) {
            if (humansRelationships[Math.max(person, friend)][Math.min(person, friend)]) {
                friends.add(friend);
            }
        }
        return friends;
    }

    //remove people from set, with which you have already had relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humansRelationships.length; i++) {
            if ((i < index) && (index < humansRelationships.length) && humansRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humansRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    //return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}