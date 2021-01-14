using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using NotificationsService.Services;
using System.Threading.Tasks;

namespace NotificationsService.Controllers
{
    [ApiController]
    [Authorize]
    [Route("[controller]")]
    public class NotificationsController : ControllerBase
    {
        private readonly INotificationService _notificationsService;

        public NotificationsController(INotificationService notificationsService)
            => _notificationsService = notificationsService;


        [HttpGet("Get")]
        public async Task<IActionResult> Get()
        {
            var usernameClaim = HttpContext.User.FindFirst("sub");
            if (usernameClaim == null || string.IsNullOrWhiteSpace(usernameClaim.Value))
            {
                return BadRequest();
            }

            var notifications = await _notificationsService.GetAsync(usernameClaim.Value);
            return new JsonResult(notifications);
        }
    }
}
