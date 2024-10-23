package com.pcwk.ehr.movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.member.MemberVO;

public class MovieDao implements WorkDiv<MovieVO> {
    public static List<MovieVO> movieList = new ArrayList<>();
    private static final String MOVIE_FILE_PATH = "movie.csv";
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public int doSave(MovieVO vo) {
        movieList.add(vo);
        return writeFile(MOVIE_FILE_PATH);
    }

    @Override
    public int doUpdate(MovieVO movie) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getMovieName().equalsIgnoreCase(movie.getMovieName())) {
                movieList.set(i, movie);
                return writeFile(MOVIE_FILE_PATH); // 영화 정보와 좌석 상태를 저장
            }
        }
        return 0; // 업데이트 실패 시 0 반환
    }

    @Override
    public int doDelete(MovieVO vo) {
        MovieVO movieToDelete = doSelectOne(vo);
        if (movieToDelete != null) {
            movieList.remove(movieToDelete);
            return writeFile(MOVIE_FILE_PATH);
        }
        return 0; // 삭제 실패 시 0 반환
    }

    @Override
    public MovieVO doSelectOne(MovieVO vo) {
        for (MovieVO movie : movieList) {
            if (movie.getMovieName().equalsIgnoreCase(vo.getMovieName())) {
                return movie;
            }
        }
        return null; // 검색 실패 시 null 반환
    }

    @Override
    public List<MovieVO> doRetrieve(DTO dto) {
        return movieList; // 모든 영화 목록을 반환
    }

    @Override
    public int writeFile(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (MovieVO movie : movieList) {
                String movieData = String.format("%s,%s,%s,%d,%.2f,%s",
                        movie.getMovieName(),
                        movie.getDate().format(dateFormatter),
                        movie.getSupervision(),
                        movie.getAgeLimit(),
                        movie.getRating(),
                        movie.seatsToString()); // 좌석 정보를 CSV로 저장
                bw.write(movieData);
                bw.newLine();
            }
            return 1; // 파일 저장 성공 시 1 반환
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
            return 0; // 파일 저장 실패 시 0 반환
        }
    }

    @Override
    public int readFile(String path) {
        movieList.clear(); // 기존 데이터를 초기화
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                MovieVO movie = new MovieVO(data[0], LocalDate.parse(data[1]), data[2],
                        Integer.parseInt(data[3]), Double.parseDouble(data[4]));
                movie.stringToSeats(data[5]); // 좌석 정보를 복원
                movieList.add(movie);
            }
            return 1; // 파일 로드 성공 시 1 반환
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류가 발생했습니다: " + e.getMessage());
            return 0; // 파일 로드 실패 시 0 반환
        }
    }

	@Override
	public int doUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO doSelectOne(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}