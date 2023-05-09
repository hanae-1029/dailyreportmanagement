package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Report> getReportList() {
        // リポジトリのfindAllメソッドを呼び出す
        return reportRepository.findAll();
    }

    /** 詳細 更新 */
    public Report getReport(Integer id) {
        // findById(id) 1件検索
        return reportRepository.findById(id).get();
    }

    /** 新規登録を行なう */
    @Transactional
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

}
