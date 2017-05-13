package com.output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Output7", urlPatterns = {"/Output7"})
public class Output7 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Output7() {
    super();
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext sc = getServletContext();
    HttpSession session = request.getSession();
    try {
      // communicate the result of authentication
      Optional<String> OptUName = Optional.of(request.getParameter("atai1"));
      Optional<String> OptUPassKey = Optional.of(request.getParameter("atai2"));
      String uName = OptUName.get();
      String uPassKey = OptUPassKey.get();

      boolean validUser = validUser(uName);
      boolean validPassKey = validPassKey(uName, uPassKey);

      session.setAttribute("userid", uName);
      session.setAttribute("passwd", uPassKey);

      if (uName.length() == 0 || uPassKey.length() == 0) {
        // not yet entered
        session.setAttribute("comment", "どちらかのテキストボックスが未入力です");
        sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
      } else {
        if (validUser) {
          if (validPassKey) {
            // id and pw are OK
            session.setAttribute("comment", "ユーザーIDとパスワードが正しいです");
            sc.getRequestDispatcher("/OutputOK.jsp").forward(request, response);
          } else {
            // id is OK, but pw is NG
            session.setAttribute("comment", "パスワードが間違えています");
            sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
          }
        } else {
          // id is NG, so skip to pw validation
          session.setAttribute("comment", "ユーザーが存在しません");
          sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
        }
      }
    } catch (NullPointerException e) {
      // do not have textbox
      session.setAttribute("comment", "どちらかのテキストボックスが存在しません");
      sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
    }
  }

  private boolean validUser(String uName) {
    boolean result = false;
    List<String> userPassList = new ArrayList<>();
    userPassList.add("HAL");
    userPassList.add("OSAKA");
    userPassList.add("MODE");
    userPassList.add("TOKYO");
    userPassList.add("ISEN");
    userPassList.add("NAGOYA");

    int userIndex = userPassList.indexOf(uName);
    if (userIndex % 2 == 0) {
      result = true;
    }

    return result;
  }

  private boolean validPassKey(String uName, String uPassKey) {
    boolean result = false;
    List<String> userPassList = new ArrayList<>();
    userPassList.add("HAL");
    userPassList.add("OSAKA");
    userPassList.add("MODE");
    userPassList.add("TOKYO");
    userPassList.add("ISEN");
    userPassList.add("NAGOYA");

    int userIndex = userPassList.indexOf(uName);
    if (userIndex % 2 != 0) {
      int userIndexPlus = userPassList.indexOf(uName) + 1;
      int uPassKeyIndex = userPassList.indexOf(uPassKey);
      if (userIndexPlus == uPassKeyIndex) {
        result = true;
      }
    }
    return result;
  }
}
