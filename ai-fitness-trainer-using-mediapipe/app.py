import os
import cv2
import uuid
from flask import Flask, jsonify, request
from utils.utils import get_mediapipe_pose
from project.process_frame import ProcessFrame
from project.thresholds import get_thresholds_beginner, get_thresholds_pro

app = Flask(__name__)
@app.route("/")

def hello():
    return "我的第一个Flask项目!"

# 共享存储配置
UPLOAD_FOLDER = './shared_storage/upload'
PROCESSED_FOLDER = './shared_storage/processed/'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
os.makedirs(PROCESSED_FOLDER, exist_ok=True)

@app.route('/process', methods=['POST'])
def process_video():
    try:
        print("[DEBUG] 接收到的完整请求数据:", request.json)
        print("1. 开始处理请求...")
        data = request.json
        input_path = data['video_path']
        mode = data['mode']
        print(f"2. 输入路径: {input_path}, 模式: {mode}")

        if not os.path.exists(input_path):
            print("3. 文件不存在！")
            return jsonify({'error': 'File not found'}), 400

        print("4. 初始化阈值和MediaPipe...")
        thresholds = get_thresholds_beginner() if mode == 'beginner' else get_thresholds_pro()
        pose = get_mediapipe_pose()  # <-- 检查此处是否崩溃
        print("5. MediaPipe Pose 初始化成功！")
        processor = ProcessFrame(thresholds=thresholds)
        print("6. 处理器初始化成功！")

        output_filename = f"processed_{uuid.uuid4()}.mp4"
        output_path = os.path.join(PROCESSED_FOLDER,output_filename)
        print(f"7. 输出路径: {output_path}")

        print("8. 开始处理视频...")
        process_video_file(input_path, output_path, processor, pose)  # <-- 检查此处是否崩溃
        print("9. 视频处理完成！")

        return jsonify({'status': 'success', 'processed_path': output_filename})

    except Exception as e:
        print(f"10. 捕获到异常: {str(e)}")
        return jsonify({'error': str(e)}), 500

def process_video_file(input_path, output_path, processor, pose):
    cap = cv2.VideoCapture(input_path)
    fps = int(cap.get(cv2.CAP_PROP_FPS))
    frame_size = (
        int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)),
        int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    )
    
    fourcc = cv2.VideoWriter_fourcc(*'H264')
    out = cv2.VideoWriter(output_path, fourcc, fps, frame_size)

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break
            
        frame_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        processed_frame, _ = processor.process(frame_rgb, pose)
        out.write(cv2.cvtColor(processed_frame, cv2.COLOR_RGB2BGR))

    cap.release()
    out.release()

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000, debug=True, use_reloader=False)
