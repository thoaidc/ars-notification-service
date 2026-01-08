package com.ars.notificationservice.repository.impl;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.ConversationDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.repository.NotificationRepositoryCustom;
import com.dct.config.common.SqlUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {
    private final EntityManager entityManager;

    public NotificationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<ConversationDTO> getAllConversationWithPaging(SearchChatMessageRequest request) {
        String querySql = """
            SELECT
                IF(n.sender_id = ?, n.receiver_id, n.sender_id) AS partnerId,
                IF(n.sender_id = ?, n.receiver_name, n.sender_name) AS partnerName,
                MAX(n.created_date) AS latestMessageTime
            FROM notification n
            WHERE ? IN (n.sender_id, n.receiver_id)
            GROUP BY 1, 2
            ORDER BY latestMessageTime DESC
        """;
        Query query = this.entityManager.createNativeQuery(querySql, "conversationGetWithPaging");
        SqlUtils.setPageable(query, request.getPageable());
        query.setParameter(1, request.getCurrentUserId());
        query.setParameter(2, request.getCurrentUserId());
        query.setParameter(3, request.getCurrentUserId());
        //noinspection unchecked
        return new PageImpl<ConversationDTO>(query.getResultList(), request.getPageable(), 0);
    }

    @Override
    public Page<ChatMessageDTO> getAllConversationMessageWithPaging(SearchChatMessageRequest request) {
        String countSql = "SELECT COUNT(*) FROM notification n ";
        String querySql = """
            SELECT n.id,
                  n.sender_id as senderId,
                  n.sender_name as senderName,
                  n.receiver_id as receiverId,
                  n.receiver_name as receiverName,
                  n.content,
                  n.created_date as createdDate
            FROM notification n
        """;
        Map<String, Object> params = new HashMap<>();
        StringBuilder whereConditions = new StringBuilder(SqlUtils.WHERE_DEFAULT);
        params.put("userA", request.getCurrentUserId());
        params.put("userB", request.getPartnerId());
        whereConditions.append(" AND (");
        whereConditions.append("  (n.sender_id = :userA AND n.receiver_id = :userB) ");
        whereConditions.append("  OR ");
        whereConditions.append("  (n.sender_id = :userB AND n.receiver_id = :userA) ");
        whereConditions.append(" )");
        SqlUtils.setOrderByDecreasing(whereConditions, "n.id");
        return SqlUtils.queryBuilder(entityManager)
                .countQuerySql(countSql + whereConditions)
                .querySql(querySql + whereConditions)
                .params(params)
                .pageable(request.getPageable())
                .getResultsWithPaging("chatMessageGetWithPaging");
    }
}
