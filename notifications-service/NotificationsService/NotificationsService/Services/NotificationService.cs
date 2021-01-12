using AutoMapper;
using Dapper;
using Microsoft.Extensions.Configuration;
using MySql.Data.MySqlClient;
using NotificationsService.Entities;
using NotificationsService.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace NotificationsService.Services
{
    public interface INotificationService
    {
        Task<IEnumerable<Notification>> GetAsync(int userId);
    }

    internal class NotificationService : INotificationService
    {
        private readonly string _connectionString;
        private readonly IMapper _mapper;
        public NotificationService(IConfiguration configuration, IMapper mapper)
        {
            _connectionString = configuration.GetConnectionString("Main");
            _mapper = mapper;
        }

        public async Task<IEnumerable<Notification>> GetAsync(int userId)
        {
            var sql = $"SELECT `{NotificationEntity.COLUMN_USERID}`, `{NotificationEntity.COLUMN_NOTIFICATIONS}` " +
                $"FROM `{NotificationEntity.TABLE_NAME}` WHERE `{NotificationEntity.COLUMN_USERID}` = @UserId;";
            
            using var connection = new MySqlConnection(_connectionString);
            var notifications = await connection.QueryAsync<NotificationEntity>(sql, new { UserId = userId });

            return _mapper.Map<IEnumerable<Notification>>(notifications);
        }
    }
}
