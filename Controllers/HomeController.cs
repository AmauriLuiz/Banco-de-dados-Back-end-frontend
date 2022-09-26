using System.Web.Mvc;

namespace AgenciaViagens.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Fale conosco!! Será um prazer lhe atender *---*";

            return View();
        }
    }
}