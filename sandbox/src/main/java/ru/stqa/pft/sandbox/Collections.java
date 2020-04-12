package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    //String[] langs = new String[4];
    //langs[0] = "Java";
    //langs[1] = "C#";
    //langs[2] = "Python";
    //langs[3] = "PHP";
    String[] langs = {"Java", "C#", "Python", "PHP"};
    //List<String> languages = new ArrayList<>();
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    //languages.add("Java");
    //languages.add("C#");
    //languages.add("Python");

    for(String l: languages) {
      System.out.println("Я хочу выучить " + l);
    }

    for(int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }
  }
}
