package com.output;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Output5", urlPatterns = {"/Output5"})
public class Output5 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Output5() {
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
    try {
      // communicate the result of authentication
      Optional<String> OptUName = Optional.of(request.getParameter("atai1"));
      Optional<String> OptUPassKey = Optional.of(request.getParameter("atai2"));
      String uName = OptUName.get();
      String uPassKey = OptUPassKey.get();

      boolean validUser = validUser(uName);
      boolean validPassKey = validPassKey(uName, uPassKey);

      request.setAttribute("userid", uName);
      request.setAttribute("passwd", uPassKey);

      if (uName.length() == 0 || uPassKey.length() == 0) {
        // not yet entered
        request.setAttribute("comment", "どちらかのテキストボックスが未入力です");
        sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
      } else {
        if (validUser) {
          if (validPassKey) {
            // id and pw are OK
            request.setAttribute("comment", "ユーザーIDとパスワードが正しいです");
            sc.getRequestDispatcher("/OutputOK.jsp").forward(request, response);
          } else {
            // id is OK, but pw is NG
            request.setAttribute("comment", "パスワードが間違えています");
            sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
          }
        } else {
          // id is NG, so skip to pw validation
          request.setAttribute("comment", "ユーザーが存在しません");
          sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
        }
      }
    } catch (NullPointerException e) {
      // do not have textbox
      request.setAttribute("comment", "どちらかのテキストボックスが存在しません");
      sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
    }
  }

  private boolean validUser(String uName) {
    boolean result = false;
    String[][] strUserPassList = {{"HAL", "OSAKA"}, {"MODE", "TOKYO"}, {"ISEN", "NAGOYA"}};
    for (String[] strUserPass : strUserPassList) {
      if (uName.equals(strUserPass[0])) {
        result = true;
      }
    }
    return result;
  }

  private boolean validPassKey(String uName, String uPassKey) {
    boolean result = false;
    String[][] strUserPassList = {{"HAL", "OSAKA"}, {"MODE", "TOKYO"}, {"ISEN", "NAGOYA"}};
    for (String[] strUserPass : strUserPassList) {
      if (uName.equals(strUserPass[0])) {
        if (uPassKey.equals(strUserPass[1])) {
          result = true;
        }
      }
    }
    return result;
  }
}
