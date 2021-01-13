using System.ComponentModel.DataAnnotations.Schema;

namespace NotificationsService.Entities
{
    public class NotificationEntity
    {
        public const string TABLE_NAME = "user_notifications";

        public const string COLUMN_USERID = "user_id";
        public const string COLUMN_NOTIFICATIONS = "notifications"; 

        [Column(COLUMN_USERID)]
        public int UserId { get; set; }

        [Column(COLUMN_NOTIFICATIONS)]
        public string Content{ get; set; }
    }
}
