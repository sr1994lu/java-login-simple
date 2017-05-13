package com.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Output6", urlPatterns = {"/Output6"})
public class Output6 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Output6() {
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
    HashMap<String, String> userPassList = new HashMap<>();
    userPassList.put("HAL", "OSAKA");
    userPassList.put("MODE", "TOKYO");
    userPassList.put("ISEN", "NAGOYA");

    if (userPassList.containsKey(uName)) {
      result = true;
    }

    return result;
  }

  private boolean validPassKey(String uName, String uPassKey) {
    boolean result = false;
    HashMap<String, String> userPassList = new HashMap<>();
    userPassList.put("HAL", "OSAKA");
    userPassList.put("MODE", "TOKYO");
    userPassList.put("ISEN", "NAGOYA");

    if (userPassList.containsKey(uName)) {
      if (userPassList.containsValue(uPassKey)) {
        result = true;
      }
    }
    return result;
  }
}
