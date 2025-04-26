<!-- ChatWindow.vue -->
<template>
  <div class="chat-container">
    <form @submit.prevent="handleSubmit">
      <input type="hidden" :value="sessionId">
      <textarea 
        v-model="userMsg" 
        :disabled="isSending"
        placeholder="输入消息..."
      ></textarea>
      <button type="submit" :disabled="!userMsg || isSending">
        {{ isSending ? '发送中...' : '发送' }}
      </button>
    </form>

    <div class="message-history">
      <div v-for="(message, index) in history" :key="message.id || index" class="message-item">
        <div v-if="message.type === 'user'" class="user-message">
          <span class="role">用户:</span>
          <div class="content">{{ message.content }}</div>
        </div>
        
        <div v-else class="ai-message">
          <div class="header">
            <span class="role">AI:</span>
            <button 
              v-if="message.revocable"
              @click="handleRevoke(message.id)"
              class="revoke-btn"
            >
              撤回
            </button>
          </div>
          <div class="content">{{ message.content }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import store from "@/store";
export default {
  name: 'ChatWindow',
  props: {
    initialSessionId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      userMsg: '',
      history: [],
      sessionId: this.initialSessionId,
      isSending: false
    };
  },
  methods: {
  async handleSubmit() {
    if (!this.userMsg.trim()) return;

    try {
      this.isSending = true;
      
      // 添加用户消息（临时ID）
      const userMessage = {
        type: 'user',
        content: this.userMsg,
        tempId: store.state.user.id, 
        timestamp: new Date().getTime()
      };
      this.history.push(userMessage);

      const payload = {
        userMsg: this.userMsg,
        sessionId: this.sessionId
      };

      this.userMsg = ''; // 清空输入框

      // 发送POST请求
      const response = await axios.post('/AI/AI-LLM', payload, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      // 处理响应数据
      if (response.data && response.data.length >= 2) {
        const [metadata, aiResponse] = response.data;
        
        const aiMessage = {
          type: 'ai',
          id: metadata.id,
          content: aiResponse.content,
          revocable: true,
          timestamp: new Date().getTime(),
          relatedUserMsgId: userMessage.tempId // 关联用户消息
        };
        
        this.history.push(aiMessage);
      }
    } catch (error) {
      console.error('发送消息失败:', error);
      this.history.push({
        type: 'system',
        content: '消息发送失败: ' + (error.response?.data?.error || '服务异常'),
        isError: true
      });
    } finally {
      this.isSending = false;
    }
  },

  async handleRevoke(messageId) {
    try {
      await axios.delete(`/revoke2/${messageId}`);
      
      // 找到AI消息索引
      const aiIndex = this.history.findIndex(
        msg => msg.type === 'ai' && msg.id === messageId
      );

      if (aiIndex !== -1) {
        // 查找关联的用户消息
        const userIndex = this.history.findIndex(
          msg => msg.tempId === this.history[aiIndex].relatedUserMsgId
        );
        
        if (userIndex !== -1) {
          this.history.splice(userIndex, aiIndex - userIndex + 1);
        }
      }
    } catch (error) {
      console.error('撤回失败:', error);
      this.history.push({
        type: 'system',
        content: '撤回操作失败: ' + (error.response?.data?.error || '服务异常'),
        isError: true
      });
    }
   }
 }
};
</script>

<style scoped>
.chat-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

form {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

textarea {
  flex: 1;
  height: 80px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}

.message-history {
  border: 1px solid #eee;
  border-radius: 4px;
  padding: 15px;
}

.message-item {
  margin-bottom: 15px;
}

.user-message, .ai-message {
  padding: 10px;
  border-radius: 4px;
  margin: 10px 0;
}

.user-message {
  background-color: #f8f9fa;
}

.ai-message {
  background-color: #e9ecef;
  border-left: 3px solid #007bff;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
}

.role {
  font-weight: bold;
  color: #2c3e50;
}

.revoke-btn {
  padding: 4px 8px;
  font-size: 0.8em;
  background-color: #dc3545;
}

.content {
  white-space: pre-wrap;
  line-height: 1.6;
}
</style>