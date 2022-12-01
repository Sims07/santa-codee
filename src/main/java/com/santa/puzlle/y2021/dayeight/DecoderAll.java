package com.santa.puzlle.y2021.dayeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DecoderAll {

  private List<String> digits;
  private List<String> inputs;
  private Map<Integer, String> res = new HashMap<>();

  public DecoderAll load(String input) {
    final String[] inputData = input.split("\\| ");
    this.digits = Arrays.stream(inputData[0].split(" ")).toList();

    this.inputs = Arrays.stream(inputData[1].split(" ")).toList();
    solve();
    return this;
  }

  public void solve() {
    this.digits.forEach(
        input -> {
          if (input.length() == 4) {
            res.put(4, input);
          } else if (input.length() == 3) {
            res.put(7, input);
          } else if (input.length() == 7) {
            res.put(8, input);
          } else if (input.length() == 2) {
            res.put(1, input);
          }
        });
    // solve 6 and 9 and 0
    final List<String> sixOrNine =
        this.digits.stream().filter(input -> input.length() == 6).toList();
    var zeroOrNine = new ArrayList<String>();
    sixOrNine.forEach(
        cur -> {
          if (intersect(cur, res.get(1)).length == 1) {
            res.put(6, cur);
          } else {
            zeroOrNine.add(cur);
          }
        });
    for (String digit : this.digits) {
      if (digit.length() == 5 && intersect(res.get(6), digit).length == 5) {
        res.put(5, digit);
      }
    }
    final List<String> twoOrThree =
        this.digits.stream()
            .filter(input -> input.length() == 5 && !input.equals(res.get(5)))
            .toList();
    final Character e = difference(res.get(5), res.get(6))[0];
    twoOrThree.forEach(
        cur -> {
          if (cur.contains(String.valueOf(e))) {
            res.put(2, cur);
          } else {
            res.put(3, cur);
          }
        });
    zeroOrNine.forEach(
        cur -> {
          if (cur.contains(String.valueOf(e))) {
            res.put(0, cur);
          } else {
            res.put(9, cur);
          }
        });
  }

  private Character[] difference(String s1, String s2) {
    Character[] res = diff(s1, s2);
    if (res.length == 0) {
      res = diff(s2, s1);
    }
    return res;
  }

  private Character[] diff(String s1, String s2) {
    HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
    for (int i = 0; i < s1.length(); i++) {
      h1.add(s1.charAt(i));
    }
    for (int i = 0; i < s2.length(); i++) {
      h2.add(s2.charAt(i));
    }
    h1.removeAll(h2);
    return h1.toArray(new Character[0]);
  }

  public Character[] intersect(String s1, String s2) {
    HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
    for (int i = 0; i < s1.length(); i++) {
      h1.add(s1.charAt(i));
    }
    for (int i = 0; i < s2.length(); i++) {
      h2.add(s2.charAt(i));
    }
    h1.retainAll(h2);
    return h1.toArray(new Character[0]);
  }

  public int total() {
    return Integer.parseInt(
        inputs.stream()
            .map(
                input ->
                    res.entrySet().stream()
                        .filter(entry -> sameSet(entry.getValue(), input))
                        .map(Entry::getKey)
                        .findAny()
                        .orElseThrow())
            .toList()
            .toString()
            .replace("]", "")
            .replace(" ", "")
            .replace(",", "")
            .replace("[", ""));
  }

  private boolean sameSet(String s1, String s2) {
    HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
    for (int i = 0; i < s1.length(); i++) {
      h1.add(s1.charAt(i));
    }
    for (int i = 0; i < s2.length(); i++) {
      h2.add(s2.charAt(i));
    }

    return h1.equals(h2);
  }
}
