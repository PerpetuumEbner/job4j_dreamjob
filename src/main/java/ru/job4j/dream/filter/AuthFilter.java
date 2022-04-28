package ru.job4j.dream.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    /**
     * В классе происходит проверка, чтобы только авторизованный пользователь мог смотреть и
     * добавлять вакансии и кандидатов.
     *
     * @param request  Определяет объект для предоставления сервлету информации о клиентском запросе.
     * @param response Определяет объект, помогающий сервлету отправить ответ клиенту.
     * @param chain    Объект, предоставляемый контейнером сервлета разработчику,
     *                 дающий представление о цепочке вызовов отфильтрованного запроса на ресурс.
     * @author yustas
     */
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("loginPage") || uri.endsWith("login")) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}