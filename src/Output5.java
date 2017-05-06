import java.io.IOException;
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
    String uName = request.getParameter("atai1");
    String uPassKey = request.getParameter("atai2");
    boolean validUser = validUser(uName);
    boolean validPassKey = validPassKey(uPassKey);

    // communicate the result of authentication
    ServletContext sc = getServletContext();
    request.setAttribute("userid", uName);
    request.setAttribute("passwd", uPassKey);
    if (validUser) {
      if (validPassKey) {
        // id and pw are OK
        request.setAttribute("comment", "idとpwは正常です");
        sc.getRequestDispatcher("/OutputOK.jsp").forward(request, response);
      } else {
        // id is OK, but pw is NG
        request.setAttribute("comment", "idは正常ですがpwは異常です");
        sc.getRequestDispatcher("/OutputNG.jsp").forward(request, response);
      }
    } else {
      // id is NG, so skip to pw validation
      request.setAttribute("comment", "idが異常なのでpwの検証は実施されませんでした");
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

  private boolean validPassKey(String uPassKey) {
    boolean result = false;
    String[][] strUserPassList = {{"HAL", "OSAKA"}, {"MODE", "TOKYO"}, {"ISEN", "NAGOYA"}};
    for (String[] strUserPass : strUserPassList) {
      if (uPassKey.equals(strUserPass[1])) {
        result = true;
      }
    }
    return result;
  }
}
