import java.io.IOException;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Output8", urlPatterns = {"/Output8"})
public class Output8 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Output8() {
    super();
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    String uName = request.getParameter("atai1");
    String uPassKey = request.getParameter("atai2");
    boolean validUser = validUser(uName);
    boolean validPassKey = validPassKey(uPassKey);

    // communicate the result of authentication
    ServletContext sc = getServletContext();
    HttpSession session = request.getSession();
    session.setAttribute("userid", uName);
    session.setAttribute("passwd", uPassKey);
    if (validUser) {
      if (validPassKey) {
        // id and pw are OK
        session.setAttribute("comment", "idとpwは正常です");
        sc.getRequestDispatcher("/OutputOKSession.jsp").forward(request, response);
        session.invalidate();
      } else {
        // id is OK, but pw is NG
        session.setAttribute("comment", "idは正常ですがpwは異常です");
        sc.getRequestDispatcher("/OutputNGSession.jsp").forward(request, response);
      }
    } else {
      // id is NG, so skip to pw validation
      session.setAttribute("comment", "idが異常なのでpwの検証は実施されませんでした");
      sc.getRequestDispatcher("/OutputNGSession.jsp").forward(request, response);
    }
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

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

    for (String userPass : userPassList) {
      if (uName.equals(userPass)) {
        result = true;
      }
    }
    return result;
  }

  private boolean validPassKey(String uPassKey) {
    boolean result = false;
    List<String> userPassList = new ArrayList<>();
    userPassList.add("HAL");
    userPassList.add("OSAKA");
    userPassList.add("MODE");
    userPassList.add("TOKYO");
    userPassList.add("ISEN");
    userPassList.add("NAGOYA");

    for (String userPass : userPassList) {
      if (uPassKey.equals(userPass)) {
        result = true;
      }
    }
    return result;
  }
}
