package edu.virginia.cs.nbateams;

import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class NBATeamExcelWriter {
    private final String excelFilename;
    private final String[] COLUMN_HEADERS =
            {"ID", "Abbrv", "City", "Name", "Conference", "Division"};
    private int rowCount;
    private Workbook workbook;
    private Sheet worksheet;

    public NBATeamExcelWriter(String excelFilename) {
        this.excelFilename = excelFilename;
        this.rowCount = 0;
    }

    public void writeNBATeamsToFile(List<NBATeam> nbaTeamList) throws IOException {
        workbook = getWorkbook();
        addTeamsToWorkbook(nbaTeamList);
        saveAndCloseWorkbook();
    }

    private Workbook getWorkbook() {
        SSWorkbookFactory workbookFactory = new SSWorkbookFactory();
        return workbookFactory.getWorkbook(excelFilename);
    }

    private void addTeamsToWorkbook(List<NBATeam> nbaTeamList) {
        worksheet = workbook.createSheet("NBA Teams");
        generateTitleRow();
        addTeamsToWorksheet(nbaTeamList);
        resizeColumns();
    }

    private void generateTitleRow() {
        Row titleRow = getNextRow();
        putHeaderStringArrayInFirstRow(titleRow);
    }

    private void addTeamsToWorksheet(List<NBATeam> nbaTeamList) {
        for (NBATeam team : nbaTeamList) {
            String[] teamArray = getTeamStringArray(team);
            Row newRow = getNextRow();
            putNBATeamArrayInRow(newRow, teamArray);
        }
    }

    private void saveAndCloseWorkbook() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(excelFilename);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    private void resizeColumns() {
        for (int columnIndex = 0; columnIndex < COLUMN_HEADERS.length; columnIndex++) {
            worksheet.autoSizeColumn(columnIndex);
        }
    }

    private void putHeaderStringArrayInFirstRow(Row titleRow) {
        for (int columnIndex = 0; columnIndex < COLUMN_HEADERS.length; columnIndex++) {
            Cell newCell = titleRow.createCell(columnIndex);
            newCell.setCellValue(COLUMN_HEADERS[columnIndex]);
        }
    }

    private void putNBATeamArrayInRow(Row row, String[] contents) {
        Cell idCell = row.createCell(0);
        idCell.setCellValue(Integer.parseInt(contents[0]));
        for (int columnIndex = 1; columnIndex < contents.length; columnIndex++) {
            Cell newCell = row.createCell(columnIndex);
            newCell.setCellValue(contents[columnIndex]);
        }
    }

    private Row getNextRow() {
        Row newRow = worksheet.createRow(rowCount);
        rowCount++;
        return newRow;
    }

    private String[] getTeamStringArray(NBATeam team) {
        String[] teamArray = new String[6];
        teamArray[0] = Integer.toString(team.getId());
        teamArray[1] = team.getAbbreviation();
        teamArray[2] = team.getCity();
        teamArray[3] = team.getName();
        teamArray[4] = team.getConference().name();
        teamArray[5] = team.getDivision().name();
        return teamArray;
    }
}
