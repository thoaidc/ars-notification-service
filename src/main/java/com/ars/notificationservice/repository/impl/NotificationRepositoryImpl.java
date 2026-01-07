package com.ars.notificationservice.repository.impl;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.repository.NotificationRepositoryCustom;
import com.dct.config.common.SqlUtils;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {
    private final EntityManager entityManager;

    public NotificationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<ChatMessageDTO> getAllWithPaging(SearchChatMessageRequest request) {
        String countSql = "SELECT COUNT(*) FROM notification n ";
        String querySql = """
            SELECT n.id, n.sender_id as senderId, n.receiver_id as receiverId,
            n.sender_name as senderName,
            n.receiver_name as receiverName,
            n.content, n.created_date as createdDate
            FROM notification n
        """;
        Map<String, Object> params = new HashMap<>();
        StringBuilder whereConditions = new StringBuilder(SqlUtils.WHERE_DEFAULT);

        if (request.isConversationDetail()) {
            List<Integer> ids = List.of(request.getSenderId(), request.getReceiverId());
            SqlUtils.addInCondition(whereConditions, params, "n.sender_id", ids);
            SqlUtils.addInCondition(whereConditions, params, "n.receiver_id", ids);
        } else {
            SqlUtils.addEqualCondition(whereConditions, params, "n.sender_id", request.getSenderId());
            whereConditions.append(" AND n.id IN (SELECT MAX(id) FROM notification where sender_id = n.sender_id GROUP BY receiver_id) ");
        }

        SqlUtils.setOrderByDecreasing(whereConditions, "n.id");
        return SqlUtils.queryBuilder(entityManager)
                .countQuerySql(countSql + whereConditions)
                .querySql(querySql + whereConditions)
                .params(params)
                .pageable(request.getPageable())
                .getResultsWithPaging("chatMessageGetWithPaging");
    }
}
