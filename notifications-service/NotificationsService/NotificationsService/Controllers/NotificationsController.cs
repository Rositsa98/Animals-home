using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using MySql.Data.MySqlClient;
using NotificationsService.Models;
using NotificationsService.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationsService.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class NotificationsController : ControllerBase
    {
        private readonly INotificationService _notificationsService;

        public NotificationsController(INotificationService notificationsService) 
            => _notificationsService = notificationsService;
        

        [HttpGet("Get/{userId}")]     
        public async Task<IActionResult> Get(int? userId)
        {
           if(!userId.HasValue || userId.Value == default)
            {
                return BadRequest();
            }

            var notifications = await _notificationsService.GetAsync(userId.Value);
            return new JsonResult(notifications);
        }


    }
}
