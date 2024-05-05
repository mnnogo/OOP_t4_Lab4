import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/addition"})
public class AdditionServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        JSONObject carJson = new JSONObject();
        carJson.put("brand", req.getParameter("brand"));
        carJson.put("model", req.getParameter("model"));
        carJson.put("year", req.getParameter("year"));
        carJson.put("mileage", req.getParameter("mileage"));
        carJson.put("color", req.getParameter("color"));
        carJson.put("price", req.getParameter("price"));

        // путь к файлу JSON
        String jsonPath = getJsonPath();

        // содержимое JSON
        String jsonContent = new String(Files.readAllBytes(Path.of(jsonPath)));

        // создаем JSONArray из строки JSON
        JSONArray jsonArray = new JSONArray(jsonContent);

        jsonArray.put(carJson);

        // обновить локально
        try (FileWriter writer = new FileWriter(jsonPath))
        {
            writer.write(jsonArray.toString(4));
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        // обновить на сервере
        try (FileWriter writer = new FileWriter("cars.json"))
        {
            writer.write(jsonArray.toString(4));
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        // обновить страницу для обновления таблицы
        updatePage(req, resp);
    }

    private String getJsonPath()
    {
        String parentPath;
        try
        {
            parentPath = new File(AdditionServlet.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParent();
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }

        return parentPath + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "cars.json";
    }

    private void updatePage(ServletRequest request, ServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher view = request.getRequestDispatcher("index.html");
        view.forward(request, response);
    }
}
