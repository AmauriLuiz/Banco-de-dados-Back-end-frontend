using System.Data;
using System.Linq;
using System.Net;
using System.Web.Mvc;
using AgenciaViagens.Models;
using Microsoft.AspNet.Identity;

namespace AgenciaViagens.Controllers
{
    [Authorize]
    public class AdquireController : Controller
    {
        private AgenciaViagensEntities db = new AgenciaViagensEntities();

        // rota: Minhas_Viagens, lista as viagens do cliente logado
        public ActionResult Index(bool? deleted = false)
        {
            ViewBag.MsgDelete = deleted;
            string email = User.Identity.GetUserName();
            Cliente cliente = db.Cliente.SingleOrDefault(c => c.email.Equals(email));
            if (cliente == null)
            {
                return HttpNotFound();
            }
            IQueryable<Adquire> adquire = db.Adquire.Where(a => a.cliente == cliente.id_cli);
            return View(adquire.ToList());
        }

        // rota: Minhas_Viagens/5, mostra detalhes da viagem selecionada
        public ActionResult Details(int id, bool? created = false)
        {
            ViewBag.MsgCreate = created;
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Adquire adquire = db.Adquire.Find(id);
            if (adquire == null)
            {
                return HttpNotFound();
            }
            return View(adquire);
        }

        // rota: Adquirir_Viagens/5/2, confirmação da aquisição da viagem
        public ActionResult Create(int id, int? id2)//id_viag, id_promo
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            string email = User.Identity.GetUserName();
            Cliente cliente = db.Cliente.SingleOrDefault(c => c.email.Equals(email));
            if (cliente == null)
            {
                return HttpNotFound();
            }
            ViewBag.cliente = cliente;

            Viagem viagem = db.Viagem.Find(id);
            if (viagem == null)
            {
                return HttpNotFound();
            }
            ViewBag.viagem = viagem;

            Destino destino = db.Destino.Find(viagem.destino);
            if (destino == null)
            {
                return HttpNotFound();
            }
            ViewBag.destino = destino;

            ViewBag.promocao = null;
            if (id2 != null)
            {
                Promocao promocao = db.Promocao.Find(id2);
                if (promocao == null)
                {
                    return HttpNotFound();
                }
                ViewBag.promocao = promocao;
            }
            return View();
        }

        // rota: [POST] Adquire/Create, dados da viagem adquirida
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id_adq,cliente,viagem,promocao")] Adquire adquire)
        {
            if (ModelState.IsValid)
            {
                db.Adquire.Add(adquire);
                db.SaveChanges();
                return RedirectToAction("Details", new { id = adquire.id_adq, created = true });
            }
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        }

        // rota: Cancelar_Viagem/5, confirmação de remoção da viagem selecionada
        public ActionResult Delete(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Adquire adquire = db.Adquire.Find(id);
            if (adquire == null)
            {
                return HttpNotFound();
            }
            return View(adquire);
        }

        // rota: [POST] Adquire/Delete/5, dados da viagem removida
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Adquire adquire = db.Adquire.Find(id);
            if (adquire == null)
            {
                return HttpNotFound();
            }
            db.Adquire.Remove(adquire);
            db.SaveChanges();
            return RedirectToAction("Index", new { deleted = true });
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}