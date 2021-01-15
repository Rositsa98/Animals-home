using AutoMapper;
using Dapper;
using Microsoft.Extensions.Configuration;
using MySql.Data.MySqlClient;
using NotificationsService.Entities;
using NotificationsService.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NotificationsService.Services
{
    public interface INotificationService
    {
        Task<IEnumerable<string>> GetAsync(string username);
        Task AddAsync(AddNotificationModel notification);
    }

    internal class NotificationService : INotificationService
    {
        private readonly string _connectionString;

        public NotificationService(IConfiguration configuration) =>
            _connectionString = configuration.GetConnectionString("Main");

        public async Task AddAsync(AddNotificationModel notification)
        {
            var sql = $"INSERT INTO `{NotificationEntity.TABLE_NAME}` " +
                $"(`{NotificationEntity.COLUMN_USERID}`, `{NotificationEntity.COLUMN_NOTIFICATIONS}`) " +
                $"VALUES (@UserId, @Content)";

            using var connection = new MySqlConnection(_connectionString);
            await connection.ExecuteAsync(sql, new { UserId = notification.UserId, Content = notification.Content });
        }

        public async Task<IEnumerable<string>> GetAsync(string username)
        {
            var sql = $"SELECT `{NotificationEntity.COLUMN_USERID}`, `{NotificationEntity.COLUMN_NOTIFICATIONS}` " +
                $"FROM `{NotificationEntity.TABLE_NAME}` as n JOIN `{UserEntity.TABLE_NAME}` as u " +
                $"on `n`.`{NotificationEntity.COLUMN_USERID}`=`u`.`{UserEntity.COLUMN_ID}` " +
                $"WHERE `{UserEntity.COLUMN_NAME}` = @Username";

            using var connection = new MySqlConnection(_connectionString);
            var notifications = await connection.QueryAsync<NotificationEntity>(sql, new { Username = username });

            return notifications.Select(n => n.Content).ToList();
        }
    }
}
