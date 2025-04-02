package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exam {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        List<Motivation> Motivations = new ArrayList<>();
        int lastId = 1;
        while (true) {
            System.out.print("명령어 )");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) {
                System.out.println("종료합니다.");
                sc.close();
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 없음");
            }
            if (cmd.equals("목록")) {
                if (Motivations.size() == 0) {
                    System.out.println("등록된 글이 없습니다.");
                    continue;
                }
                System.out.println("번호   /   작가   /   명언");
                for (int i = Motivations.size(); i > 0; i--) {
                    Motivation motivation = Motivations.get(i - 1);
                    System.out.printf("%d   /   %s   /   %s   \n", motivation.getId(), motivation.getAuthor(), motivation.getContent());
                }
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();
                int id = lastId;
                String regDate = LocalDateTime.now().toString();
                Motivation motivation = new Motivation(id, regDate, content, author);
                Motivations.add(motivation);
                System.out.println(id + "q번 명언이 등록되었습니다.");
                lastId++;
            } else if (cmd.startsWith("수정?id=")) {
                int idnum = 0;
                try {
                    idnum = Integer.parseInt(cmd.split("=")[1]);
                } catch (Exception e) {
                    System.out.println("명령어가 이상함");
                    continue;
                }
                for (Motivation motivation : Motivations) {
                    if (motivation.getId() == idnum) {
                        System.out.println("명언(기존) :" + motivation.getContent());
                        System.out.println("작가(기존) :" + motivation.getAuthor());
                        while (true) {
                            System.out.print("명언 : ");
                            String newcontent = sc.nextLine().trim();
                            if (newcontent.length() == 0) {
                                System.out.println("새로운 내용을 입력하세요");
                            } else {
                                motivation.setContent(newcontent);
                                break;
                            }
                        }
                        while (true) {
                            System.out.print("작가 : ");
                            String newauthor = sc.nextLine().trim();

                            if (newauthor.length() == 0) {
                                System.out.println("새로운 작가를 입력하세요");
                            } else {
                                motivation.setAuthor(newauthor);
                                System.out.println(motivation.getId() + "번 명언이 수정되었습니다.");
                                break;
                            }
                        }break;
                    }else {
                        System.out.println(idnum+"번 명언은 존재하지 않습니다.");
                        break;
                    }
                }

            } else if (cmd.startsWith("삭제?id=")) {
                int idnum = 0;
                try {
                    idnum = Integer.parseInt(cmd.split("=")[1]);
                } catch (Exception e) {
                    System.out.println("명령어가 이상함");
                    continue;
                }
                for (Motivation motivation : Motivations) {
                    if (motivation.getId() == idnum) {
                        Motivations.remove(motivation);
                        System.out.println(idnum + "번 명언이 삭제 되었습니다.");
                        break;
                    }else {
                        System.out.println(idnum+"번 명언은 존재하지 않습니다.");
                        break;
                    }
                }
                
            } else if (cmd.startsWith("상세보기?id=")) {
                int idnum = 0;
                try {
                    idnum = Integer.parseInt(cmd.split("=")[1]);
                } catch (Exception e) {
                    System.out.println("명령어가 이상함");
                    continue;
                }
                for (Motivation motivation : Motivations) {
                    if (motivation.getId() == idnum) {
                        System.out.println("번호 : " + motivation.getId());
                        System.out.println("날짜 : " + motivation.getRegDate());
                        System.out.println("작가 : " + motivation.getAuthor());
                        System.out.println("내용 : " + motivation.getContent());
                        break;
                    }else {
                        System.out.println(idnum + "번 명언은 존재하지 않습니다.");
                        break;
                    }
                }
                
            }else {
                System.out.println("사용할수 없는 명령어 입니다.");
            }
        }
    }
}
