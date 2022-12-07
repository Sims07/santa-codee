package com.santa.puzlle.y2022.daySeven;

import java.util.Arrays;
import java.util.List;

public record Command(Action action, List<String> argsOrRes) {

  public static final String SEP = " ";

  public static Command of(String cmd, List<String> argsOrRes) {
    final String[] cmds = cmd.split(SEP);
    if (cmds[1].equals(Action.LS.value)) {
      return new Command(Action.LS, argsOrRes);
    } else {
      return new Command(Action.CD, List.of(cmds[2]));
    }
  }

  public static Command of(String cmd, String... argsOrRes) {
    final String[] cmds = cmd.split(SEP);
    if (cmds[1].equals(Action.LS.value)) {
      return new Command(Action.LS, Arrays.stream(argsOrRes).toList());
    } else {
      return new Command(Action.CD, List.of(cmds[2]));
    }
  }
}
